DESCRIPTION = "tappas-tracers GStreamer plugin \
               compiles the tappas libgsttracer gstreamer plugin \ 
               and copies it to usr/lib/gstreamer-1.0 (gstreamer's plugins directory) "
               
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM += "file://../../LICENSE;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "git://git@github.com/hailo-ai/tappas.git;protocol=https;branch=master"
SRCREV = "d743a077044049a3b739575ea89a841e22f278b1"

inherit hailotools-base


do_install:append() {
    # Meson installs shared objects in apps target,
    # we remove it from the rootfs to prevent duplication with libgsthailotools
    rm -rf ${D}/usr/lib/libgsthailometa*

    rm -f ${D}/${libdir}/gstreamer-1.0/libgstsharktracers.so
    find ${D}/${libdir}/gstreamer-1.0/ -name 'libgstsharktracers.so.[0-9]' -delete
    mv -f ${D}/${libdir}/gstreamer-1.0/libgstsharktracers.so.${PV} ${D}/${libdir}/gstreamer-1.0/libgstsharktracers.so
}


DEPENDS += "glib-2.0-native glib-2.0 gstreamer1.0 gstreamer1.0-plugins-base libgsthailotools"

TAPPAS_BUILD_TARGET = "tracers"

FILES:${PN} += "/usr/lib/gstreamer-1.0/libgstsharktracers.so /usr/lib/gstreamer-1.0/libgstsharktracers.so.${PV}"
FILES:${PN}-lib += "/usr/lib/gstreamer-1.0/libgstsharktracers.so.${PV} /usr/lib/gstreamer-1.0/libgstsharktracers.so"
RDEPENDS:${PN}-staticdev = ""
RDEPENDS:${PN}-dev = ""
RDEPENDS:${PN}-dbg = ""
