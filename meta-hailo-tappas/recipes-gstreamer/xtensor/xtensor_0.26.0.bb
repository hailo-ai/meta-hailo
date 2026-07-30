SUMMARY = "Xtensor : C++ library meant for numerical analysis with multi-dimensional array expressions"
HOMEPAGE = "https://github.com/xtensor-stack/xtensor"

SRCREV_xtensor = "f31d415a507b84d0097436a38293df3f56906ad1"
SRC_URI = "git://github.com/xtensor-stack/xtensor.git;name=xtensor;protocol=https;branch=master"

LICENSE = "LICENSE"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5c67ec4d3eb9c5b7eed4c37e69571b93"

RDEPENDS:${PN} += "xtl"

do_install(){
    install -d ${D}${includedir}/xtensor
    cp -r ${S}/include/xtensor/* ${D}${includedir}/xtensor
}

FILES:${PN} += "/usr/include/* /usr/include/xtensor/*"
