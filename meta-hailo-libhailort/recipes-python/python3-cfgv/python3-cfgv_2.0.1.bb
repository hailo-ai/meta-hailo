# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Validate configuration and produce human readable error messages."
HOMEPAGE = "https://github.com/asottile/cfgv"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=92844452d544a48a78abc3a862fbd8a4"

SRC_URI = "https://files.pythonhosted.org/packages/c9/dd/d4c048c42263ef4894ee6761414d20af67682924f7a6fcac45f307729411/cfgv-${PV}.tar.gz"
SRC_URI[md5sum] = "f02861b97b78cae2843cebd5b98bdb56"
SRC_URI[sha256sum] = "edb387943b665bf9c434f717bf630fa78aecd53d5900d2e05da6ad6048553144"

S = "${WORKDIR}/cfgv-${PV}"

inherit setuptools3

# WARNING: the following rdepends are from setuptools install_requires. These
# upstream names may not correspond exactly to bitbake package names.
RDEPENDS:${PN} += "${PYTHON_PN}-six"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS:${PN} += "${PYTHON_PN}-contextlib2 ${PYTHON_PN}-core ${PYTHON_PN}-io"


