DESCRIPTION = "libhailort - hailo’s API for running inference on the hailo8 chip \
               the recipe compiles libhailort and copies it on the target device's root file system"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=48b1c947c88868c23e4fb874890be6fc \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=ea42899a0b22dc2df0970ea074a38645"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=develop"
SRCREV = "2af498cd35803c297d31d852ec414582acaa8958"

S = "${WORKDIR}/git"

inherit hailort-base
OECMAKE_TARGET_COMPILE = "libhailort"
HAILORT_INCLUDE_STAGING_DIR = "${D}${includedir}/hailort"

do_install_append() {
  install -d ${D}${libdir}
  install -m 0755 ${LIB_SRC_DIR}/libhailort.so.${PV} ${D}${libdir}
  ln -s -r ${D}${libdir}/libhailort.so.${PV} ${D}${libdir}/libhailort.so
  
  install -d ${HAILORT_INCLUDE_STAGING_DIR}
  cp -r ${S}/hailort/libhailort/include/* ${HAILORT_INCLUDE_STAGING_DIR}/
}

FILES_${PN} += "${libdir}/libhailort.so.${PV}"
FILES_${PN}-dev += "${includedir}/hailort ${includedir}/hailort/* ${libdir}/libhailort.so"
