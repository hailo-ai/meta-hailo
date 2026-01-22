DESCRIPTION = "gsthailotools GStreamer plugin \
               compiles the tappas libgsthailotools gstreamer plugin \ 
               and copies it to usr/lib/gstreamer-1.0 (gstreamer's plugins directory) "

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM += "file://../../LICENSE;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "git://git@github.com/hailo-ai/tappas.git;protocol=https;branch=master"
SRCREV = "70c2d5bf2ad31fa47e4c1dc06fd5574055ea8772"

SRC_URI += " \
    file://0001-Fix-build-with-newer-xtensor-versions.patch;patchdir=${S}/../.. \
    file://0001-Allow-setting-site-packages-dir-externally-to-meson.patch;patchdir=${S}/../.. \
    file://0002-Do-not-use-architecture-based-subdirs.patch;patchdir=${S}/../.. \
"

inherit hailotools-base python3native

do_install:append() {
    rm -f ${D}/${libdir}/gstreamer-1.0/libgsthailotools.so
    rm -f ${D}/${libdir}/gstreamer-1.0/libgsthailotools.so.[0-9]
    mv -f ${D}/${libdir}/gstreamer-1.0/libgsthailotools.so.${PV} ${D}/${libdir}/gstreamer-1.0/libgsthailotools.so
}


DEPENDS += "glib-2.0-native glib-2.0 gstreamer1.0 gstreamer1.0-plugins-base rapidjson cppzmq zeromq"
DEPENDS += "python3 python3-pybind11 python3-pygobject"
EXTRA_OEMESON += " \
    -Dlibrapidjson='${STAGING_INCDIR}/rapidjson' \
    -Dinclude_python=true \
    -Dpython_version=${PYTHON_BASEVERSION} \
    -Dpython_site_packages_dir=${PYTHON_SITEPACKAGES_DIR} \
    -Dpybind11=${STAGING_LIBDIR}/.. \
    "

# libgsthailotools requires opencv, xtensor, xtl, and libgsthailo to compile and run
TAPPAS_BUILD_TARGET = "plugins"

FILES:${PN} += "${libdir}/libgsthailometa.so.${PV} ${libdir}/libhailo_tracker.so.${PV} ${libdir}/libhailo_opencv_utils.so.${PV} \
                ${libdir}/gstreamer-1.0/libgsthailotools.so"
FILES:${PN}-lib += "${libdir}/libgsthailometa.so.${PV} ${libdir}/libhailo_tracker.so.${PV} ${libdir}/libhailo_opencv_utils.so.${PV} \
                    ${libdir}/gstreamer-1.0/libgsthailotools.so"

PACKAGES =+ "pyhailo pyhailotracker"

FILES:pyhailo = "${PYTHON_SITEPACKAGES_DIR}/hailo.*.so ${libdir}/gstreamer-1.0/libgsthailopython.so"
FILES:pyhailotracker = "${PYTHON_SITEPACKAGES_DIR}/pyhailotracker.*.so"
RDEPENDS:pyhailotracker = "${PN} hailo-post-processes"

RDEPENDS:${PN}-staticdev = ""
RDEPENDS:${PN}-dev = ""
RDEPENDS:${PN}-dbg = ""
