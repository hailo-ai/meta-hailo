SUMMARY = "LibJuice is a simple STUN/TURN client library."
DESCRIPTION = "LibJuice is a lightweight STUN/TURN client library written in C."
HOMEPAGE = "https://github.com/paullouisageneau/libjuice"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI = "git://github.com/paullouisageneau/libjuice.git;protocol=https;branch=master"
SRCREV = "126082ab7a913e04cdde3b6dfba04870ff537b08"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DBUILD_TESTS=OFF \
"

# Package definitions
FILES_${PN} += "${libdir}/libjuice.so.*"
FILES_${PN}-dev += "${includedir} ${libdir}/cmake ${libdir}/pkgconfig ${libdir}/libjuice.so"
FILES_${PN}-staticdev += "${libdir}/libjuice.a"
FILES_${PN}-dbg += "${libdir}/.debug ${bindir}/.debug"

