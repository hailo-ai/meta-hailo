HAILO_BITBUCKET_BRANCH ?= "develop"
SRC_URI = "git://git@bitbucket.org/hailotech/tappas-hrt.git;protocol=ssh;branch=${HAILO_APPS_CORE_BITBUCKET_BRANCH}"
SRCREV = "${@d.getVar('SRCREV:pn-hailo-apps-core') if d.getVar('SRCREV:pn-hailo-apps-core') is not None else '${AUTOREV}'}"
INSANE_SKIP:${PN} += "incompatible-license"
do_populate_lic[noexec] = "1"

HAILO_APPS_CORE_DIR = "${WORKDIR}/git"
