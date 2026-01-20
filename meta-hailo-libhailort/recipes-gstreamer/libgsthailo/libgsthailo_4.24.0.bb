DESCRIPTION = "gsthailo GStreamer plugin \
               compiles the hailo gstreamer plugin including hailonet \
               the output of the compilation (libgsthailo.so) is copied to the target's rootfs under usr/lib/gstreamer-1.0 (gstreamer's plugins directory)"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=ed57bbf10be0c74ecf2c80710208b2b3 \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=bdbc7f982b701bd5c908f8d9ad7f629e \
                    file://hailort/libhailort/bindings/gstreamer/LICENSE;md5=4b54a1fd55a448865a0b32d41598759d"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=hailo8"
SRCREV = "63adffec12da88f1e7cfdc07f2e07681671e83e8"

SRC_URI += " \
	file://0001-Use-a-pre-built-protobuf-dependency.patch \
	file://0002-Use-a-pre-built-spdlog-dependency.patch \
	file://0003-Use-a-pre-built-cli11-dependency.patch \
	file://0004-Use-a-pre-built-xxhash-dependency.patch \
	file://0005-Use-a-pre-built-eigen-dependency.patch \
	file://0006-Use-a-pre-built-nlohmann-json-dependency.patch \
	file://0007-Use-a-pre-built-dotwriter-dependency.patch \
	file://0008-Use-a-pre-built-readerwriterqueue-dependency.patch \
	file://0009-Build-parts-of-hailort-with-C-17.patch \
	file://0013-Use-a-pre-built-grpc-dependency.patch \
"

inherit hailort-base

# recipe is dependent on libhailort shared object file
DEPENDS = "glib-2.0 gstreamer1.0 gstreamer1.0-plugins-base glib-2.0-native"
DEPENDS += " \
	protobuf-native protobuf libeigen cli11 nlohmann-json spdlog xxhash \
	readerwriterqueue \
	${@ 'grpc-native grpc' if d.getVar('HAILO_BUILD_SERVICE') != '0' else ''} \
"

RDEPENDS:${PN} += "libhailort"

EXTRA_OECMAKE:append = " -DHAILO_BUILD_GSTREAMER=1"
OECMAKE_TARGET_COMPILE = "gsthailo"

GST_HAILO_SOURCES_DIR = "${S}/hailort/libhailort/bindings/gstreamer/gst-hailo"
GST_HAILO_INCLUDE_STAGING_DIR = "${D}${includedir}/gst-hailo"
GST_HAILO_INCLUDE_STAGING_INCLUDE_DIR = "${GST_HAILO_INCLUDE_STAGING_DIR}/metadata"

# Building against newer abseil-cpp versions require -fpermissive
# due to this and similar errors:
#      | .../libhailort/4.23.0/recipe-sysroot/usr/include/absl/log/internal/log_message.h:256:8:
#      | error: invalid conversion from 'const char*' to 'int' [-fpermissive]
#  256 |   void CopyToEncodedBuffer(absl::string_view str) ABSL_ATTRIBUTE_NOINLINE;
#      |        ^~~~~~~~~~~~~~~~~~~
#      |        |
#      |        const char*
CXXFLAGS += "-fpermissive"

# Skip cmake do_install process - overrides cmake bbclass
cmake_do_install() {
  :
}

do_install() {
    # copy libgsthailo shared object to usr/lib/gstreamer-1.0 in the rootfs - so gstreamer could load it
    install -d ${D}${libdir}/gstreamer-1.0
    install -m 0755 ${LIB_SRC_DIR}libgsthailo.so  ${D}${libdir}/gstreamer-1.0

    install -d ${GST_HAILO_INCLUDE_STAGING_DIR}
    install -d ${GST_HAILO_INCLUDE_STAGING_INCLUDE_DIR}
    cd ${GST_HAILO_SOURCES_DIR}
    find . -type f -name \*.hpp -exec install -D {} ${GST_HAILO_INCLUDE_STAGING_DIR}/{} \;
    # copy hailo_gst.h file with its dir to the directory of tensor_meta.hpp
    find . -type f -name \*.h -exec install -D {} ${GST_HAILO_INCLUDE_STAGING_INCLUDE_DIR}/{} \;
}

FILES:${PN} += "${libdir}/gstreamer-1.0/libgsthailo.so"
FILES:${PN}-dev += "${includedir}/gst-hailo ${includedir}/gst-hailo/*"
