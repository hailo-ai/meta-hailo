# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Portable network interface information."
HOMEPAGE = "http://alastairs-place.net/netifaces"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e4677613c25bf3673bfee98c0cc52202"

SRC_URI = "https://github.com/al45tair/netifaces/archive/release_0_10_9.tar.gz"
SRC_URI[md5sum] = "67468890eb744dd3cc7f4683a1d426a7"
SRC_URI[sha256sum] = "8667602d3e184d9b246ca39bbfb1dd6681aa4efd2dd4a4eda9c23a5880a86ad6"

S = "${WORKDIR}/netifaces-release_0_10_9"

inherit setuptools3

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python-core"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    netifaces
