# tappas-apps base class - setting the base configuration for meson (target, type, includes etc...)
# deppends on meta-hailo-libhailort recipes, opencv, xtensor and xtl

PV_PARSED = "${@ '${PV}'.replace('.0', '')}"
SRC_URI = "git://git@github.com/hailo-ai/tappas.git;protocol=https;branch=master-vpu"

SRCREV = "a3f33a010784c0e11c7f10e9d5d47e4583649877"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM += "file://../../LICENSE;md5=4fbd65380cdd255951079008b364516c"

DEPENDS += " gstreamer1.0 gstreamer1.0-plugins-base cxxopts rapidjson libgsthailotools libmedialib-api"
RDEPENDS:${PN} += " bash libgsthailotools"

ROOTFS_APPS_DIR = "${D}/home/root/apps"

APPS_DIR_PREFIX = "${WORKDIR}/git/apps/"
HAILO15_DIR = "${APPS_DIR_PREFIX}/h15/gstreamer/"

REQS_PATH = "${FILE_DIRNAME}/files/"
REQS_HAILO15_FILE = "${REQS_PATH}download_reqs_hailo15.txt"

REQS_FILE ?= ""
ARM_APPS_DIR ?= ""
#python set_reqs_file() {
#}

CURRENT_APP_NAME = ""
CURRENT_REQ_FILE = ""

# meson configuration
EXTRA_OEMESON += " \
        -Dapps_install_dir='/home/root/apps' \
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
    if ls ${ARM_APPS_DIR}/${CURRENT_APP_NAME}/*.sh >/dev/null 2>&1; then
    	install -m 0755 ${ARM_APPS_DIR}/${CURRENT_APP_NAME}/*.sh ${ROOTFS_APPS_DIR}/${CURRENT_APP_NAME}
    else
        bbnote ".sh file not found, skipping install"
    fi
    if [ -d "${ARM_APPS_DIR}/${CURRENT_APP_NAME}/configs" ]; then
        install -d ${ROOTFS_APPS_DIR}/${CURRENT_APP_NAME}/resources/configs
        install -m 0755 ${ARM_APPS_DIR}/${CURRENT_APP_NAME}/configs/* ${ROOTFS_APPS_DIR}/${CURRENT_APP_NAME}/resources/configs
    fi
}

do_install:append() {
    # Meson installs shared objects in apps target,
    # we remove it from the rootfs to prevent duplication with libgsthailotools
    rm -rf ${D}/usr/lib/libgsthailometa*
    rm -rf ${D}/usr/lib/pkgconfig
    rm -rf ${D}/usr/include/gsthailometa*
    rm -rf ${D}/usr/include/hailo
    rm -rf ${D}/usr/lib/libhailo_tracker*
}

python do_set_requirements_src_uris() {
    bb.build.exec_func("set_reqs_file", d)
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
    bb.build.exec_func("set_reqs_file", d)
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
