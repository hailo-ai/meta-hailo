require conf/include/tappas-src-internal.inc
S = "${TAPPAS_DIR}/core/hailo"

INTERNAL_APPS_PATH := "${THISDIR}/files/"
INTERNAL_APPS_HAILO15_FILE = "${INTERNAL_APPS_PATH}internal_apps_hailo15.txt"
INTERNAL_REQS_HAILO15_FILE = "${INTERNAL_APPS_PATH}internal_reqs_hailo15.txt"
INTERNAL_APPS_FILE = ""
INTERNAL_REQS_FILE = ""
python () {
    if 'hailo15' in d.getVar('MACHINE'):
        d.setVar('INTERNAL_APPS_FILE', d.getVar('INTERNAL_APPS_HAILO15_FILE'))
        d.setVar('INTERNAL_REQS_FILE', d.getVar('INTERNAL_REQS_HAILO15_FILE'))
}

do_fetch[prefuncs] += "do_set_internal_reqs_src_uris"
do_unpack[prefuncs] += "do_set_internal_reqs_src_uris"
do_cleanstate[prefuncs] += "do_set_internal_reqs_src_uris"
do_cleanall[prefuncs] += "do_set_internal_reqs_src_uris"
do_clean[prefuncs] += "do_set_internal_reqs_src_uris"

fakeroot install_internal_apps() {
    # install app path on the rootfs
    install -v -d ${ROOTFS_APPS_DIR}/${CURRENT_APP_NAME}
    install -v -d ${ROOTFS_APPS_DIR}/${CURRENT_APP_NAME}/resources

    # copy the app shell script into the app path
    install -v -m 0755 ${ARM_APPS_DIR}/${CURRENT_APP_NAME}/*.sh ${ROOTFS_APPS_DIR}/${CURRENT_APP_NAME}
    if [ -d "${ARM_APPS_DIR}/${CURRENT_APP_NAME}/configs" ]; then
        install -v -d ${ROOTFS_APPS_DIR}/${CURRENT_APP_NAME}/resources/configs
        install -v -m 0755 ${ARM_APPS_DIR}/${CURRENT_APP_NAME}/configs/*.json ${ROOTFS_APPS_DIR}/${CURRENT_APP_NAME}/resources/configs
    fi
}

fakeroot install_internal_reqs() {
    # install app path on the rootfs
    install -v -d ${ROOTFS_APPS_DIR}/${CURRENT_APP_NAME}
    install -v -d ${ROOTFS_APPS_DIR}/${CURRENT_APP_NAME}/resources

    # copy the required file into the app path under resources directory
    install -m 0755 ${WORKDIR}/${CURRENT_REQ_FILE} ${ROOTFS_APPS_DIR}/${CURRENT_APP_NAME}/resources
}

# Add internal requirements as a new task
addtask install_internal_requirements after do_install_requirements before do_package

do_install_internal_requirements[depends]+=" virtual/fakeroot-native:do_populate_sysroot"

fakeroot python do_install_internal_requirements() {
    apps_file = d.getVar('INTERNAL_APPS_FILE')
    if apps_file:
        with open(apps_file, "r") as apps_file:
            for app_name in apps_file:
                # iterate over apps_file.txt, parse each line
                app_name_stripped = app_name.strip()
                d.setVar('CURRENT_APP_NAME', app_name_stripped)
                bb.build.exec_func('install_internal_apps', d)

    reqs_file = d.getVar('INTERNAL_REQS_FILE')
    if reqs_file:
        with open(reqs_file, "r") as reqs_file:
            for line in reqs_file:
                # iterate over internal_reqs.txt, parse each line
                stripped_line = line.strip().split(' -> ')
                req_file = stripped_line[0].split('/')[-1]
                app_path = stripped_line[1]
                app_name = app_path.split('/', 3)[-1]

                # set app name and file variables and call install_app_dir
                d.setVar('CURRENT_APP_NAME', app_name)
                d.setVar('CURRENT_REQ_FILE', req_file)
                bb.build.exec_func('install_internal_reqs', d)
}

python do_set_internal_reqs_src_uris() {
    reqs_file = d.getVar('INTERNAL_REQS_FILE')

    if reqs_file:
        with open(reqs_file, "r") as reqs_file:
            for line in reqs_file:
                # iterate over internal_reqs.txt, parse each line
                stripped_line = line.strip().split(' -> ')
                url = stripped_line[0]
                md5sum = stripped_line[2]
                # set src_uri from app uri + md5sum, do_fetch task will use it
                src_uri = ' {};md5sum={}'.format(url, md5sum)
                d.appendVar('SRC_URI', src_uri)
}