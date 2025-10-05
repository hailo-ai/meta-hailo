DESCRIPTION = "libhailort - hailo’s API for running inference on the hailo8 chip \
               the recipe compiles libhailort and copies it on the target device's root file system"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=ed57bbf10be0c74ecf2c80710208b2b3 \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=87f8edc8e3d5342f8b0614df5bae3b58"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=hailo8"
SRCREV = "08f088d3b443c7846af067269ce998c6d5d91449"

S = "${WORKDIR}/git"

inherit hailort-base
OECMAKE_TARGET_COMPILE = "libhailort"
HAILORT_INCLUDE_STAGING_DIR = "${D}${includedir}"
HAILORT_EXPORT_DIR = "${D}${libdir}/cmake/HailoRT"

do_install:append() {
  install -d ${D}${libdir}
  install -m 0755 ${LIB_SRC_DIR}/libhailort.so.${PV} ${D}${libdir}
  ln -s -r ${D}${libdir}/libhailort.so.${PV} ${D}${libdir}/libhailort.so

  install -d ${HAILORT_INCLUDE_STAGING_DIR}
  cp -r ${S}/hailort/libhailort/include/* ${HAILORT_INCLUDE_STAGING_DIR}/

  install -d ${HAILORT_EXPORT_DIR}
  install -m 0644 ${WORKDIR}/build/hailort/libhailort/src/*.cmake ${HAILORT_EXPORT_DIR}
  install -m 0644 ${WORKDIR}/build/hailort/libhailort/src/CMakeFiles/Export/**/*.cmake ${HAILORT_EXPORT_DIR}

}

FILES:${PN} += "${libdir}/libhailort.so.${PV}"
FILES:${PN}-dev += "${includedir}/hailort ${includedir}/hailort/* ${libdir}/libhailort.so"
