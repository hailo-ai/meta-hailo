# libdatachannel_1.0.bb

SUMMARY = "C++ library for WebRTC Data Channels"
DESCRIPTION = "Libdatachannel is a small C++ library that allows the use of WebRTC Data Channels in native applications."
HOMEPAGE = "https://github.com/paullouisageneau/libdatachannel"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI = "git://github.com/paullouisageneau/libdatachannel.git;protocol=https;branch=master"
SRCREV = "57e06d83789e146e22d155d237afb3cea9a21037"

DEPENDS = "openssl libjuice libsrtp plog usrsctp cmake-native ninja-native"


S = "${WORKDIR}/git"

inherit cmake pkgconfig

# CMake configuration options based on BUILDING.md instructions
EXTRA_OECMAKE = "\
    -DWEBRTC_BUILD=ON \
    -DUSRSCTP_BUILD=ON \
    -DUSE_NICE=OFF \
    -DCMAKE_BUILD_TYPE=Release \
    -DCMAKE_INSTALL_PREFIX=${D}${prefix} \
    -DBUILD_TESTS=OFF \
    -DPREFER_SYSTEM_LIB=ON \
    -DNO_EXAMPLES=ON \
    -DNO_TESTS=ON \
    -G Ninja \
"

do_install(){
     # Create the directory for libraries
    install -d ${D}${libdir}

    # Install the actual shared library
    install -m 0755 ${B}/libdatachannel.so.0.21.2 ${D}${libdir}

    # Create the symlinks for versioning
    ln -sf libdatachannel.so.0.21.2 ${D}${libdir}/libdatachannel.so.0.21
    ln -sf libdatachannel.so.0.21 ${D}${libdir}/libdatachannel.so

    # Create the directory for headers
    install -d ${D}${includedir}
    install -d ${D}${includedir}/rtc

    # Install the header files
    install -m 0644 ${S}/include/rtc/*.h ${D}${includedir}/rtc
    install -m 0644 ${S}/include/rtc/*.hpp ${D}${includedir}/rtc
}

FILES:${PN}-dev += "${includedir}/* ${includedir}/rtc/*.h ${includedir}/rtc/*.hpp"
FILES:${PN} += "${includedir}/* ${includedir}/rtc/*.h ${includedir}/rtc/*.hpp"