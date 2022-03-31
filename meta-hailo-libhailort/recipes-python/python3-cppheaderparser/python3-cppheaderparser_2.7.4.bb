# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Parse C++ header files and generate a data structure representing the class"
HOMEPAGE = "http://senexcanis.com/open-source/cppheaderparser/"
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

SRC_URI = "https://files.pythonhosted.org/packages/3c/ba/d8d168a4b54cae66eaf13d1d9197ca9349c94653815e061f79e7eed86c01/CppHeaderParser-${PV}.tar.gz"
SRC_URI[md5sum] = "53bbc8984ccb61b37444a4e8110d2591"
SRC_URI[sha256sum] = "382b30416d95b0a5e8502b214810dcac2a56432917e2651447d3abe253e3cc42"

S = "${WORKDIR}/CppHeaderParser-${PV}"

do_install_prepend() {
    rm -rf ${S}/CppHeaderParser/examples
}

do_install_append () {
    # remove all module examples path
    rm -rf ${D}${libdir}/${PYTHON_DIR}/site-packages/CppHeaderParser/examples
    rm -rf ${PKGD}${libdir}/${PYTHON_DIR}/site-packages/CppHeaderParser/examples
    rm -rf ${PKGDEST}${libdir}/${PYTHON_DIR}/site-packages/CppHeaderParser/examples
    rm -rf ${SYSROOT_DESTDIR}${libdir}/${PYTHON_DIR}/site-packages/CppHeaderParser/examples
    rm -rf ${S}/build/lib/CppHeaderParser/examples
}

inherit setuptools3

# WARNING: the following rdepends are from setuptools install_requires. These
# upstream names may not correspond exactly to bitbake package names.
RDEPENDS_${PN} += " ${PYTHON_PN}-ply python3"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    ply.lex
