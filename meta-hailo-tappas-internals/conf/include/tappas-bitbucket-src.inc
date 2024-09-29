HAILO_BITBUCKET_BRANCH ?= "develop"
SRC_URI = "git://git@bitbucket.org/hailotech/tappas-hrt.git;protocol=ssh;branch=${TAPPAS_BITBUCKET_BRANCH}"
SRCREV = "${@d.getVar('SRCREV:pn-tappas') if d.getVar('SRCREV:pn-tappas') is not None else '${AUTOREV}'}"
INSANE_SKIP:${PN} += "incompatible-license"
do_populate_lic[noexec] = "1"

TAPPAS_DIR = "${WORKDIR}/git"