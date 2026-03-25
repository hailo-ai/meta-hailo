DESCRIPTION = "libhailopp - hailo's post-processing library \
               the recipe compiles libhailopp and copies it on the target device's root file system"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=800c77403398cedcbbbcd86d37f5e0ff \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=eb78bffb175a3f2be317bb4c45fedecf"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=master-v5.3.0"
SRCREV = "3ad6b763a9ad671a90bc8b167872605378558f97"

S = "${WORKDIR}/git"

inherit hailort-base
OECMAKE_TARGET_COMPILE = "hailopp"
SO_MAJOR_VERSION = "${@d.getVar('PV').split('.')[0]}"
HAILOPP_INCLUDE_STAGING_DIR = "${D}${includedir}/hailopp"
HAILOPP_EXPORT_DIR = "${D}${libdir}/cmake/HailoPP"

do_install:append() {
  install -d ${D}${libdir}
  install -m 0755 ${LIB_SRC_DIR}/libhailopp.so.${PV} ${D}${libdir}
  ln -s -r ${D}${libdir}/libhailopp.so.${PV} ${D}${libdir}/libhailopp.so.${SO_MAJOR_VERSION}
  ln -s -r ${D}${libdir}/libhailopp.so.${PV} ${D}${libdir}/libhailopp.so

  install -d ${HAILOPP_INCLUDE_STAGING_DIR}
  cp -r ${S}/hailort/libhailopp/include/* ${HAILOPP_INCLUDE_STAGING_DIR}/

  install -d ${HAILOPP_EXPORT_DIR}
  install -m 0644 ${WORKDIR}/build/hailort/libhailopp/src/*.cmake ${HAILOPP_EXPORT_DIR}
  install -m 0644 ${WORKDIR}/build/hailort/libhailopp/src/CMakeFiles/Export/lib/cmake/HailoPP/*.cmake ${HAILOPP_EXPORT_DIR}

}

FILES:${PN} += "${libdir}/libhailopp.so.${PV} ${libdir}/libhailopp.so.${SO_MAJOR_VERSION}"
FILES:${PN}-dev += "${includedir}/hailopp/ ${libdir}/libhailopp.so"
