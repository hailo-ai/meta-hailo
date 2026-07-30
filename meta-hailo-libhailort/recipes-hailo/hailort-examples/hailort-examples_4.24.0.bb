DESCRIPTION = "hailort examples - C and C++ example programs and test models"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=ed57bbf10be0c74ecf2c80710208b2b3 \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=bdbc7f982b701bd5c908f8d9ad7f629e"

SRC_URI = "git://github.com/hailo-ai/hailort.git;protocol=https;branch=hailo8"
SRCREV = "63adffec12da88f1e7cfdc07f2e07681671e83e8"

SRC_URI += " \
	https://hailo-hailort.s3.eu-west-2.amazonaws.com/Hailo8/${PV}/HEFS/shortcut_net.hef;name=shortcut_net \
	https://hailo-hailort.s3.eu-west-2.amazonaws.com/Hailo8/${PV}/HEFS/shortcut_net_nv12.hef;name=shortcut_net_nv12 \
	https://hailo-hailort.s3.eu-west-2.amazonaws.com/Hailo8/${PV}/HEFS/multi_network_shortcut_net.hef;name=multi_network_shortcut_net \
	https://hailo-hailort.s3.eu-west-2.amazonaws.com/Hailo8/${PV}/HEFS/resnet_v1_18.hef;name=resnet_v1_18 \
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

SRC_URI[shortcut_net.sha256sum] = "f45d88f827770ef89f008ca74555a09985ed5f63e39f6a3e73f983a2cfa738bd"
SRC_URI[shortcut_net_nv12.sha256sum] = "25d389d75e9e7d70ba106d58655cc9110d1f988a32532f3a8d92372156b677f1"
SRC_URI[multi_network_shortcut_net.sha256sum] = "e02cd75bad6f034259b6aa67103db42f9748ceb02e948d08727bfea5db6fc346"
SRC_URI[resnet_v1_18.sha256sum] = "20256ea3bee60d9ef22c19c6feb83600a76147980428439f77a6c6840ac1f027"

DEPENDS += " \
	protobuf-native protobuf libeigen cli11 nlohmann-json spdlog xxhash \
	readerwriterqueue \
	${@ 'grpc-native grpc' if d.getVar('HAILO_BUILD_SERVICE') != '0' else ''} \
"

inherit hailort-base

EXTRA_OECMAKE += "-DHAILO_BUILD_EXAMPLES=ON"

RDEPENDS:${PN} += "libhailort"
OECMAKE_TARGET_COMPILE = "hailort_examples"

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

do_install:append() {
  install -d ${D}${datadir}/${PN}/examples/hefs
  install -m0644 ${UNPACKDIR}/{shortcut_net,shortcut_net_nv12,multi_network_shortcut_net}.hef ${D}${datadir}/${PN}/examples/hefs
  install -d ${D}${datadir}/${PN}/tutorials/hefs
  install -m0644 ${UNPACKDIR}/{resnet_v1_18,shortcut_net}.hef ${D}${datadir}/${PN}/tutorials/hefs

  install -d ${D}${bindir}/${PN}/{c,cpp}
  install -m755 ${BIN_SRC_DIR}/c_* ${D}${bindir}/${PN}/c/
  install -m755 ${BIN_SRC_DIR}/cpp_* ${D}${bindir}/${PN}/cpp/
}

FILES:${PN} += "${datadir}/${PN}/examples ${datadir}/${PN}/tutorials"
