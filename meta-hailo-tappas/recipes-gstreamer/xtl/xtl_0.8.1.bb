SUMMARY = "Xtl: Basic tools (containers, algorithms) used by other quantstack packages"
HOMEPAGE = "https://github.com/xtensor-stack/xtl"

SRCREV_xtl = "f3ca44e9102d574e9592c6917ae09da2bacf9aae"
SRC_URI = "git://github.com/xtensor-stack/xtl.git;protocol=https;name=xtl;branch=master"

LICENSE = "LICENSE"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c12cbcb0f50cce3b0c58db4e3db8c2da"

do_install(){
    install -d ${D}${includedir}/xtl
    cp -r ${S}/include/xtl/* ${D}${includedir}/xtl
}

FILES:${PN} += "/usr/include/* /usr/include/xtl/*"
