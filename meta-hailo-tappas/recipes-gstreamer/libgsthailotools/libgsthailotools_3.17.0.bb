DESCRIPTION = "gsthailotools GStreamer plugin \
               compiles the hailo tools gstreamer plugin including hailofilter hailomuxer \ 
               and copies it to usr/lib/gstreamer-1.0 (gstreamer's plugins directory) \
               compiles the hailo post processes, including draw processes, and various network postprocesses \
               and copies it to usr/lib/hailo-post-processes"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM += "file://../../../LICENSE;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "git://git@github.com/hailo-ai/tappas.git;protocol=https;branch=master"
SRCREV = "ba1f6a8dd02bfc245ddab953609b0610983aad0c"

inherit meson pkgconfig

S = "${WORKDIR}/git/core/hailo/gstreamer"

# libgsthailotools requires opencv, xtensor, xtl, and libgsthailo to compile and run
DEPENDS = "glib-2.0 gstreamer1.0 gstreamer1.0-plugins-base libgsthailo libhailort \
           glib-2.0-native opencv xtensor xtl"

GST_HAILO_INCLUDE_DIR = "${STAGING_INCDIR}/gst-hailo/metadata"
HAILO_INCLUDE_DIR = "${STAGING_INCDIR}/hailort"

PARALLEL_MAKE = "-j 4"

# meson configuration
EXTRA_OEMESON += " \
        -Dlibargs='-I${GST_HAILO_INCLUDE_DIR},-I${HAILO_INCLUDE_DIR}' \
        -Dlibxtensor='${STAGING_INCDIR}/xtensor' \
        -Dinclude_blas=false \
        -Dinclude_network_switch_app=false \
        -Dcpp_std='c++17' \
        --buildtype='release' \
        "

do_install_append() {
    # copy libgsthailotools shared object to usr/lib/gstreamer-1.0 in the rootfs - so gstreamer could load it
    # copy postprocess libs to usr/lib/hailo-post-processes
    install -d ${D}${libdir}/gstreamer-1.0
    install -d ${D}${libdir}/hailo-post-processes
    install -m 0755 ${B}/plugins/libgsthailotools.so  ${D}${libdir}/gstreamer-1.0
    install -m 0755 ${B}/plugins/libgsthailometa.so  ${D}${libdir}/gstreamer-1.0
    cp ${B}/libs/*.so  ${D}${libdir}/hailo-post-processes
}

FILES_${PN} += "/usr/lib/* /usr/lib/gstreamer-1.0/* /usr/lib/gstreamer-1.0/libgsthailotools.so /usr/lib/gstreamer-1.0/libgsthailometa.so /usr/lib/hailo-post-processes/* /usr/lib/hailo-post-processes/so.*"
