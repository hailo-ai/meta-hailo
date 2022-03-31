# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "A few extensions to pyyaml."
HOMEPAGE = "https://github.com/asottile/aspy.yaml"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ec6e4db5d90408877624a1ff7086f824"

SRC_URI = "https://files.pythonhosted.org/packages/a1/e9/2ee775d3e66319e08135505a1dd3cdba606b4da4caeb617eb3514d901b14/aspy.yaml-${PV}.tar.gz"
SRC_URI[md5sum] = "1959a34873a6bdc1fba807023804867e"
SRC_URI[sha256sum] = "e7c742382eff2caed61f87a39d13f99109088e5e93f04d76eb8d4b28aa143f45"

S = "${WORKDIR}/aspy.yaml-${PV}"

inherit setuptools3

# WARNING: the following rdepends are from setuptools install_requires. These
# upstream names may not correspond exactly to bitbake package names.
RDEPENDS_${PN} += "${PYTHON_PN}-pyyaml"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "${PYTHON_PN}-core"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    yaml


