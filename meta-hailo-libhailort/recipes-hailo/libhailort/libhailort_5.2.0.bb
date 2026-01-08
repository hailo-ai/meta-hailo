DESCRIPTION = "libhailort - hailo’s API for running inference on the hailo8 chip \
               the recipe compiles libhailort and copies it on the target device's root file system"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=800c77403398cedcbbbcd86d37f5e0ff \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=c858d970eda804f02813be8e047fa07d"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=master"
SRCREV = "41a720b9fedb56a4ee9ea39506afecf3f9ace2eb"

S = "${WORKDIR}/git"

inherit hailort-base
OECMAKE_TARGET_COMPILE = "libhailort"
HAILORT_INCLUDE_STAGING_DIR = "${D}${includedir}"
HAILORT_EXPORT_DIR = "${D}${libdir}/cmake/HailoRT"
RDEPENDS:${PN} += " libatomic"

PACKAGECONFIG ??= ""
PACKAGECONFIG[perfetto] = ",,libperfetto,libperfetto"
python() {
    if bb.utils.contains('PACKAGECONFIG', 'perfetto', True, False, d):
        d.appendVar('EXTRA_OECMAKE', ' -DENABLE_PERFETTO=ON')
}

do_install:append() {
  install -d ${D}${libdir}
  install -m 0755 ${LIB_SRC_DIR}/libhailort.so.${PV} ${D}${libdir}
  ln -s -r ${D}${libdir}/libhailort.so.${PV} ${D}${libdir}/libhailort.so
  
  install -d ${HAILORT_INCLUDE_STAGING_DIR}
  cp -r ${S}/hailort/libhailort/include/* ${HAILORT_INCLUDE_STAGING_DIR}/

  install -d ${HAILORT_EXPORT_DIR}
  install -m 0644 ${WORKDIR}/build/hailort/libhailort/src/*.cmake ${HAILORT_EXPORT_DIR}
  install -m 0644 ${WORKDIR}/build/hailort/libhailort/src/CMakeFiles/Export/lib/cmake/HailoRT/*.cmake ${HAILORT_EXPORT_DIR}

}

FILES:${PN} += "${libdir}/libhailort.so.${PV}"
FILES:${PN}-dev += "${includedir}/hailort ${includedir}/hailort/* ${libdir}/libhailort.so"
