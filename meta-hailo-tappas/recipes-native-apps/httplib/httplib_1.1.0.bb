SUMMARY = "A C++11 single-file header-only cross platform HTTP/HTTPS library"

LICENSE = "LICENSE"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1321bdf796c67e3a8ab8e352dd81474b"

SRC_URI = "git://github.com/yhirose/cpp-httplib.git;protocol=https;branch=master"

PV = "1.0+git${SRCPV}"
SRCREV = "ad40bd6a009c5fd237f2972987a528d59a8526ba"

S = "${WORKDIR}/git"

# expected is a header-only C++ library, so the main package will be empty.
ALLOW_EMPTY:${PN} = "1"
ALLOW_EMPTY:${PN}-dev = "1"

do_install(){
    install -d ${D}${includedir}
    install -m 0644 ${S}/httplib.h ${D}${includedir}/
}

FILES:${PN} += "${includedir}/* ${includedir}/httplib.h"
FILES:${PN}-dev += "${includedir}/*"
