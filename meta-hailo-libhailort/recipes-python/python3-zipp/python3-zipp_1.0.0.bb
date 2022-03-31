# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Backport of pathlib-compatible object wrapper for zip files"
HOMEPAGE = "https://github.com/jaraco/zipp"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a33f38bbf47d48c70fe0d40e5f77498e"

SRC_URI = "https://files.pythonhosted.org/packages/d4/cd/ef86396dce8910413b6ca1ef31ec09367c47e15fc1a12def2cc8ae134dea/zipp-${PV}.tar.gz"
SRC_URI[md5sum] = "49b29a0469c1af728fdf681187fb3a5e"
SRC_URI[sha256sum] = "d38fbe01bbf7a3593a32bc35a9c4453c32bc42b98c377f9bff7e9f8da157786c"

S = "${WORKDIR}/zipp-${PV}"

inherit setuptools3

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= "testing docs"
PACKAGECONFIG[testing] = ",,,${PYTHON_PN}-contextlib2 ${PYTHON_PN}-pathlib2 ${PYTHON_PN}-unittest2"
PACKAGECONFIG[docs] = ",,,${PYTHON_PN}-jaraco-packaging ${PYTHON_PN}-rst-linker ${PYTHON_PN}-sphinx"

DEPENDS += "${PYTHON_PN}-setuptools-scm-native"
#DEPENDS_${PN} += "python3-zipp-native"

# WARNING: the following rdepends are from setuptools install_requires. These
# upstream names may not correspond exactly to bitbake package names.
RDEPENDS_${PN} += "${PYTHON_PN}-more-itertools"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "${PYTHON_PN}-compression ${PYTHON_PN}-contextlib2 ${PYTHON_PN}-core ${PYTHON_PN}-io ${PYTHON_PN}-shell ${PYTHON_PN}-unittest"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    pathlib