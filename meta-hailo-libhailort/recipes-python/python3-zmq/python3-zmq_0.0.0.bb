# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "You are probably looking for pyzmq."
HOMEPAGE = "https://github.com/zeromq/pyzmq"
# Unable to find any files that looked like license statements. Check the accompanying
# documentation and source headers and set LICENSE and LIC_FILES_CHKSUM accordingly.
#
# NOTE: LICENSE is being set to "CLOSED" to allow you to at least start building - if
# this is not accurate with respect to the licensing of the software being built (it
# will not be in most cases) you must specify the correct value before using this
# recipe for anything other than initial testing/development!
# NOTE: Original package / source metadata indicates license is: BSD
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "https://files.pythonhosted.org/packages/a4/a1/0dcfcea4c3f547f23781877ee90b4fccc8cf32bbbc1bc529a17bd142abc1/zmq-${PV}.zip"
SRC_URI[md5sum] = "988e0eb30ad7b36880731f09f1b3df93"
SRC_URI[sha256sum] = "21cfc6be254c9bc25e4dabb8a3b2006a4227966b7b39a637426084c8dc6901f7"

S = "${WORKDIR}/zmq-${PV}"

inherit setuptools3

# WARNING: the following rdepends are from setuptools install_requires. These
# upstream names may not correspond exactly to bitbake package names.
RDEPENDS:${PN} += "${PYTHON_PN}-pyzmq"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS:${PN} += "${PYTHON_PN}-core"


