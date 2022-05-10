SUMMARY = "matplotlib: plotting with Python"
DESCRIPTION = "\
Matplotlib is a Python 2D plotting library which produces \
publication-quality figures in a variety of hardcopy formats \
and interactive environments across platforms."
HOMEPAGE = "https://github.com/matplotlib/matplotlib"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=833f9197a6a58b2220343627353cdd45"

DEPENDS = "python3-numpy-native python3-numpy freetype libpng python3-dateutil python3-pytz"
RDEPENDS:${PN} = "python3-numpy freetype libpng python3-dateutil python3-pytz"

SRC_URI[md5sum] = "fc8a614367f7ba0d34a02fd08c535afc"
SRC_URI[sha256sum] = "53eaed412477c836e1b9522c19858a8557d6e595077830146182225613b11a75"

PYPI_PACKAGE = "kiwisolver"
inherit pypi setuptools3