# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Recursively check spelling and grammer and other tasks with text documents."
HOMEPAGE = "https://github.com/scivision/textutils3"
# Unable to find any files that looked like license statements. Check the accompanying
# documentation and source headers and set LICENSE and LIC_FILES_CHKSUM accordingly.
#
# NOTE: LICENSE is being set to "CLOSED" to allow you to at least start building - if
# this is not accurate with respect to the licensing of the software being built (it
# will not be in most cases) you must specify the correct value before using this
# recipe for anything other than initial testing/development!
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "https://files.pythonhosted.org/packages/e3/e2/5cfe3b9f62527831afe8f6c239abb7655df74f7436bffcd58a1bcdde0bf4/textutils3-${PV}.tar.gz"
SRC_URI[md5sum] = "9c80e610caffa516ff827ecb3d410240"
SRC_URI[sha256sum] = "e0f676c6b03cbda3b3b99ecdeeb00a3a6dcc1bea6c3f8d9ac5ccb8e0d51cb641"

S = "${WORKDIR}/textutils3-${PV}"

inherit setuptools3

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= "tests cov"
PACKAGECONFIG[tests] = ",,,python-pytest"
PACKAGECONFIG[cov] = ",,,python-coveralls python-flake8 python-mypy python-pytest-cov"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS:${PN} += "python-core"
