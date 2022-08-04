DESCRIPTION = "libhailort - hailo’s API for running inference on the hailo8 chip \
               the recipe compiles libhailort and copies it on the target device's root file system"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=48b1c947c88868c23e4fb874890be6fc \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=82a4bdb4814fa0e0e16f3eb0a75192d2"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=develop"
SRCREV = "6346a2db99a5d4314a8dc42a34d9be7391b9a948"

S = "${WORKDIR}/git"

inherit hailort-base
OECMAKE_TARGET_COMPILE = "libhailort"
HAILORT_INCLUDE_STAGING_DIR = "${D}${includedir}/hailort"

do_install_append() {
  install -d ${D}${libdir}
  install -m 0755 ${LIB_SRC_DIR}/libhailort.so.${PV} ${D}${libdir}
  
  install -d ${HAILORT_INCLUDE_STAGING_DIR}
  cp -r ${S}/hailort/libhailort/include/* ${HAILORT_INCLUDE_STAGING_DIR}/
}

FILES_${PN} += "${libdir}/libhailort.so.${PV}"
FILES_${PN}-dev += "${includedir}/hailort ${includedir}/hailort/*"
