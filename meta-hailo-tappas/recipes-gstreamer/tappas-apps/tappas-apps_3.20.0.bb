DESCRIPTION = "TAPPAS ARM applications recipe, \
			  the recipe copies the app script, hef files and media to /home/root/apps \
              the apps hefs and media urls are taken from files/download_reqs.txt"

PV_PARSED = "${@ '${PV}'.replace('.0', '')}"
SRC_URI = "git://git@github.com/hailo-ai/tappas.git;protocol=https;branch=develop"

S = "${WORKDIR}/git/core/hailo/gstreamer"

SRCREV = "a450932c36412945ecc2647a1d3f8817818f2831"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM += "file://../../../LICENSE;md5=4fbd65380cdd255951079008b364516c"

inherit hailotools-base

# Setting meson build target as 'apps'
TAPPAS_BUILD_TARGET = "apps"

DEPENDS += " gstreamer1.0 gstreamer1.0-plugins-base cxxopts rapidjson"
RDEPENDS_${PN} += " bash libgsthailotools"

ARM_APPS_DIR = "${WORKDIR}/git/apps/gstreamer/imx"
LPR_APP_NAME = "license_plate_recognition"

OPENCV_UTIL = "libhailo_cv_singleton.so"
GST_IMAGES_UTIL = "libhailo_gst_image.so"

ROOTFS_APPS_DIR = "${D}/home/root/apps"

REQS_FILE = "${FILE_DIRNAME}/files/download_reqs.txt"
CURRENT_APP_NAME = ""
CURRENT_REQ_FILE = ""

# meson configuration
EXTRA_OEMESON += " \
        -Dapps_install_dir='/home/root/apps' \
		-Dlibcxxopts='${STAGING_INCDIR}/cxxopts' \
        -Dlibrapidjson='${STAGING_INCDIR}/rapidjson' \
        "
addtask install_requirements after do_install before do_package

do_fetch[prefuncs] += "do_set_requirements_src_uris"
do_unpack[prefuncs] += "do_set_requirements_src_uris"
do_cleanstate[prefuncs] += "do_set_requirements_src_uris"
do_cleanall[prefuncs] += "do_set_requirements_src_uris"
do_clean[prefuncs] += "do_set_requirements_src_uris"

do_install_requirements[depends]+=" virtual/fakeroot-native:do_populate_sysroot"

fakeroot install_app_dir() {
    # install app path on the rootfs
    install -d ${ROOTFS_APPS_DIR}/${CURRENT_APP_NAME}
    install -d ${ROOTFS_APPS_DIR}/${CURRENT_APP_NAME}/resources

    # copy the required file into the app path under resources directory
    install -m 0755 ${WORKDIR}/${CURRENT_REQ_FILE} ${ROOTFS_APPS_DIR}/${CURRENT_APP_NAME}/resources
    # copy the app shell script into the app path
    install -m 0755 ${ARM_APPS_DIR}/${CURRENT_APP_NAME}/*.sh ${ROOTFS_APPS_DIR}/${CURRENT_APP_NAME}
    if [ -d "${ARM_APPS_DIR}/${CURRENT_APP_NAME}/configs" ]; then
	    install -d ${ROOTFS_APPS_DIR}/${CURRENT_APP_NAME}/resources/configs
	    install -m 0755 ${ARM_APPS_DIR}/${CURRENT_APP_NAME}/configs/*.json ${ROOTFS_APPS_DIR}/${CURRENT_APP_NAME}/resources/configs
    fi 
}

do_install_append() {
    # Meson installs shared objects in apps target,
    # we remove it from the rootfs to prevent duplication with libgsthailotools
    rm -rf ${D}/usr/lib/libgsthailometa*
    rm -rf ${D}/usr/lib/libhailo_tracker*
}

python do_set_requirements_src_uris() {
    req_file = d.getVar('REQS_FILE')

    with open(req_file, "r") as req_file:
        for line in req_file:
            # iterate over download_reqs.txt, parse each line
            stripped_line = line.strip().split(' -> ')
            url = stripped_line[0]
            md5sum = stripped_line[2]
            # set src_uri from app url + md5sum, do_fetch task will use it 
            src_uri = ' {};md5sum={}'.format(url, md5sum)
            d.appendVar('SRC_URI', src_uri)
}

fakeroot python do_install_requirements() {
    req_file = d.getVar('REQS_FILE')

    with open(req_file, "r") as req_file:
        for line in req_file:
            # iterate over download_reqs.txt, parse each line
            stripped_line = line.strip().split(' -> ')
            req_file = stripped_line[0].split('/')[-1]
            app_path = stripped_line[1]
            app_name = app_path.split('/')[-1]

            # set app name and file variables and call install_app_dir
            d.setVar('CURRENT_APP_NAME', app_name)
            d.setVar('CURRENT_REQ_FILE', req_file)
            bb.build.exec_func('install_app_dir', d)
}

FILES_${PN} += " /home/root/apps/* /home/root/apps/${LPR_APP_NAME}/* /home/root/apps/${LPR_APP_NAME}/resources/* /usr/lib/${OPENCV_UTIL}.${PV} /usr/lib/${GST_IMAGES_UTIL}.${PV}"
FILES_${PN}-lib += "/usr/lib/${OPENCV_UTIL}.${PV} /usr/lib/${GST_IMAGES_UTIL}.${PV}"
RDEPENDS_${PN}-staticdev = ""
RDEPENDS_${PN}-dev = ""
RDEPENDS_${PN}-dbg = ""
