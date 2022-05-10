# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "File identification library for Python"
HOMEPAGE = "https://github.com/chriskuehl/identify"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   identify/vendor/licenses.py
# NOTE: Original package / source metadata indicates license is: MIT
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bbdc006359f3157660173ec7f133a80e \
                    file://identify/vendor/licenses.py;md5=0f9a34f66679ed3cb98082b15a54b575"

SRC_URI = "https://files.pythonhosted.org/packages/e3/b4/34bca440f89068052576d04a3cb4d2520525fa002b81cbd9d228e762c331/identify-${PV}.tar.gz"
SRC_URI[md5sum] = "947fd83dcffcebd65e5a7205e9b9eab8"
SRC_URI[sha256sum] = "8ad99ed1f3a965612dcb881435bf58abcfbeb05e230bb8c352b51e8eac103360"

S = "${WORKDIR}/identify-${PV}"

inherit setuptools3

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= "license"
PACKAGECONFIG[license] = ",,,python-editdistance"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS:${PN} += "${PYTHON_PN}-core ${PYTHON_PN}-io ${PYTHON_PN}-shell"


