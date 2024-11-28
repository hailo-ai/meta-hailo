DESCRIPTION = "TAPPAS ARM applications recipe, \
               the recipe copies the app script, hef files and media to /home/root/apps \
               the apps hefs and media urls are taken from files/download_reqs.txt"

S = "${WORKDIR}/git/core/hailo"

inherit hailotools-base tappas-apps-base

# Setting meson build target as 'apps'
TAPPAS_BUILD_TARGET = "apps"

LPR_APP_NAME = "license_plate_recognition"

OPENCV_UTIL = "libhailo_cv_singleton.so"

IMX8_DIR = "${APPS_DIR_PREFIX}/h8/gstreamer/imx8/"

REQS_IMX8_FILE = "${REQS_PATH}download_reqs_imx8.txt"

python set_reqs_file() {
    if 'imx8' in d.getVar('MACHINE'):
        d.setVar('REQS_FILE', d.getVar('REQS_IMX8_FILE'))
        d.setVar('ARM_APPS_DIR', d.getVar('IMX8_DIR'))
    else:
        d.setVar('REQS_FILE', d.getVar('REQS_HAILO15_FILE'))
        d.setVar('ARM_APPS_DIR', d.getVar('HAILO15_DIR'))
        d.appendVar('DEPENDS', " libmedialib-api xtensor")
}

IS_H15 = "${@ 'true' if 'hailo15' in d.getVar('MACHINE') else 'false'}"
INSTALL_LPR = "true"

DEPENDS += " cxxopts"
# meson configuration
EXTRA_OEMESON += " \
        -Dinstall_lpr='${INSTALL_LPR}' \
        -Dlibrapidjson='${STAGING_INCDIR}/rapidjson' \
        "

do_install:append() {
    if [ '${IS_H15}' = 'true' ]; then
        install -d ${ROOTFS_APPS_DIR}/encoder_pipelines_new_api/configs/
        install -m 0755 ${S}/apps/hailo15/encoder_pipelines_new_api/*.json ${ROOTFS_APPS_DIR}/encoder_pipelines_new_api/configs/
    fi
}

FILES:${PN} += " /home/root/apps/* /home/root/apps/${LPR_APP_NAME}/* /home/root/apps/${LPR_APP_NAME}/resources/* /usr/lib/${OPENCV_UTIL}.${PV}"
FILES:${PN}-lib += "/usr/lib/${OPENCV_UTIL}.${PV}"
RDEPENDS:${PN}-staticdev = ""
RDEPENDS:${PN}-dev = ""
RDEPENDS:${PN}-dbg = ""
