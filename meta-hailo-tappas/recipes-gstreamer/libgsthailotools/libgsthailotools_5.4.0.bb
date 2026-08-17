DESCRIPTION = "gsthailotools GStreamer plugin \
               compiles the tappas libgsthailotools gstreamer plugin \ 
               and copies it to usr/lib/gstreamer-1.0 (gstreamer's plugins directory) "

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM += "file://../../LICENSE;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "git://git@github.com/hailo-ai/tappas.git;protocol=https;branch=master"
SRCREV = "5e1caa5ec3cbd5e3e31a9d3179bab0bb459c4acc"

inherit hailotools-base

do_install:append() {
    rm -f ${D}/${libdir}/gstreamer-1.0/libgsthailotools.so
    find ${D}/${libdir}/gstreamer-1.0/ -name 'libgsthailotools.so.[0-9]' -delete
    mv -f ${D}/${libdir}/gstreamer-1.0/libgsthailotools.so.${PV} ${D}/${libdir}/gstreamer-1.0/libgsthailotools.so
}


DEPENDS += "glib-2.0-native glib-2.0 gstreamer1.0 gstreamer1.0-plugins-base rapidjson cppzmq zeromq"
EXTRA_OEMESON += " \
    -Dlibrapidjson='${STAGING_INCDIR}/rapidjson' \
    "

# libgsthailotools requires opencv, xtensor, xtl, and libgsthailo to compile and run
TAPPAS_BUILD_TARGET = "plugins"

FILES:${PN} += "${libdir}/libgsthailometa.so.${PV} ${libdir}/libhailo_tracker.so.${PV} ${libdir}/libhailo_opencv_utils.so.${PV} \
                ${libdir}/gstreamer-1.0/libgsthailotools.so"
FILES:${PN}-lib += "${libdir}/libgsthailometa.so.${PV} ${libdir}/libhailo_tracker.so.${PV} ${libdir}/libhailo_opencv_utils.so.${PV} \
                    ${libdir}/gstreamer-1.0/libgsthailotools.so"
RDEPENDS:${PN}-staticdev = ""
RDEPENDS:${PN}-dev = ""
RDEPENDS:${PN}-dbg = ""

DEPENDS:append:hailo15 = " libgstmedialib "