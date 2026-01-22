DESCRIPTION = "Tappas post processes \
               compiles the hailo post processes, including draw processes, cropping algorithms and various network postprocesses \
               and copies it to usr/lib/hailo-post-processes"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM += "file://../../LICENSE;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "git://git@github.com/hailo-ai/tappas.git;protocol=https;branch=master"
SRCREV = "70c2d5bf2ad31fa47e4c1dc06fd5574055ea8772"

SRC_URI += " \
    file://0001-Fix-build-with-newer-xtensor-versions.patch;patchdir=${S}/../.. \
    file://0002-Do-not-use-architecture-based-subdirs.patch;patchdir=${S}/../.. \
"

inherit hailotools-base

# Setting meson build target
TAPPAS_BUILD_TARGET = "libs"
ROOTFS_POST_PROCESSES_DIR = "${libdir}/hailo-post-processes"

# add dependencies
DEPENDS += "cxxopts rapidjson"
RDEPENDS:${PN} += " libgsthailotools"


# meson configuration
EXTRA_OEMESON += " \
        -Dpost_processes_install_dir='${ROOTFS_POST_PROCESSES_DIR}' \
        -Dlibcxxopts='${STAGING_INCDIR}/cxxopts' \
        -Dlibrapidjson='${STAGING_INCDIR}/rapidjson' \
        "

do_install:append() {
    # Meson installs shared objects in apps target,
    # we remove it from the rootfs to prevent duplication with libgsthailotools
    rm -rf ${D}${libdir}/libhailo_tracker*
    rm -rf ${D}/${libdir}/libhailo_opencv_utils*
    rm -rf ${D}/${libdir}/libgsthailometa*
    # These are in libgsthailotools-dev
    rm -rf ${D}${libdir}/pkgconfig
    rm -rf ${D}${includedir}
}

FILES:${PN} += "${libdir}/hailo-post-processes/* ${ROOTFS_POST_PROCESSES_DIR}/* ${ROOTFS_POST_PROCESSES_DIR}/so.* \
                ${ROOTFS_POST_PROCESSES_DIR}/cropping_algorithms/* ${ROOTFS_POST_PROCESSES_DIR}/post_processes_data/* "
