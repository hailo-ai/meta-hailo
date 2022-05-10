SUMMARY = "matplotlib: plotting with Python"
DESCRIPTION = "\
Matplotlib is a Python 2D plotting library which produces \
publication-quality figures in a variety of hardcopy formats \
and interactive environments across platforms."
HOMEPAGE = "https://github.com/matplotlib/matplotlib"
SECTION = "devel/python"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://extern/libqhull/COPYING.txt;md5=6cf68697da2f499f1207c84dc319b727 \
                    file://doc/devel/license.rst;md5=172af1d2d4892171668e22c2be84043f \
                    file://doc/users/license.rst;md5=afc4dac850850071d7c392744442e17f \
                    file://doc/_static/fa/LICENSE;md5=c26df8d430d6362b1e4dd3a4fe687db2 \
                    file://LICENSE/LICENSE_STIX;md5=9ad7e5cfe24ee2150b4408c58734efb6 \
                    file://LICENSE/LICENSE_AMSFONTS;md5=93895aa8301691bbfbf12497348d62ff \
                    file://LICENSE/LICENSE.PIL;md5=fdd193019db760773c867077026f56fa \
                    file://LICENSE/LICENSE_CONDA;md5=66b9b3ff374ef0bbc54f61b166af87fe \
                    file://LICENSE/pnpoly.license;md5=83d19344ddec62f35b1f55bba2730bc5 \
                    file://LICENSE/LICENSE_QT4_EDITOR;md5=99177999ce2f10ba740ec88a2a93bf5b \
                    file://LICENSE/LICENSE_YORICK;md5=9ea57de436161290f04d54e20b25d5b9 \
                    file://LICENSE/LICENSE_BAKOMA;md5=61a4c95c451bbd79f0f15a619dacaa1a \
                    file://LICENSE/LICENSE_PAINT;md5=735386132f9c2fbc19efd554ff58d77e \
                    file://LICENSE/LICENSE_JQUERY;md5=22ccb47559e2090b634a35fd3eaddf74 \
                    file://LICENSE/LICENSE_CARLOGO.txt;md5=02ae6df3dfda7328baaceeca7a308253 \
                    file://LICENSE/LICENSE_COLORBREWER;md5=b063f619be23bbc660415cb7f129d679 \
                    file://LICENSE/LICENSE_enthought.txt;md5=55f28694da29ad0d07a5548a4524e7da \
                    file://LICENSE/LICENSE;md5=afec61498aa5f0c45936687da9a53d74 \
                    file://lib/matplotlib/mpl-data/fonts/ttf/LICENSE_STIX;md5=7a4eaa2939f08df41f70d0dcaee76ea7 \
                    file://lib/matplotlib/mpl-data/fonts/ttf/LICENSE_DEJAVU;md5=9f867da7a73fad2715291348e80d0763 \
                    file://lib/matplotlib/backends/web_backend/jquery-ui-1.12.1/LICENSE.txt;md5=e0c0d3d883e83f19efa64feb54d5f63d"
DEPENDS = "\
    freetype \
    libpng \
    python3-numpy-native \
    python3-pytz-native \
    python3-certifi-native \
"

SRC_URI = "https://files.pythonhosted.org/packages/9c/4b/06f4aa9bef6b5e4f177881b4dedd94faa6e7cb3d95dfaeaa8a1a8b541095/matplotlib-${PV}.tar.gz"
SRC_URI[md5sum] = "b60cd68f792a30173d825e16482aedd8"
SRC_URI[sha256sum] = "3d77a6630d093d74cbbfebaa0571d00790966be1ed204e4a8239f5cbd6835c5d"

inherit pypi setuptools3 pkgconfig

# LTO with clang needs lld
LDFLAGS:append:toolchain-clang = " -fuse-ld=lld"
LDFLAGS:remove:toolchain-clang_mips = "-fuse-ld=lld"

RDEPENDS:${PN} = "\
    freetype \
    libpng \
    python3-numpy \
    python3-pyparsing \
    python3-cycler \
    python3-dateutil \
    python3-kiwisolver \
    python3-pytz \
"

ENABLELTO:toolchain-clang_riscv64 = "echo enable_lto = False >> ${S}/setup.cfg"
ENABLELTO:toolchain-clang_riscv32 = "echo enable_lto = False >> ${S}/setup.cfg"
ENABLELTO:toolchain-clang_mips = "echo enable_lto = False >> ${S}/setup.cfg"
do_compile:prepend() {
    echo [libs] > ${S}/setup.cfg
    echo system_freetype = true >> ${S}/setup.cfg
    ${ENABLELTO}
}

BBCLASSEXTEND = "native"
