DESCRIPTION = "hailort service - The Multi-Process Service enables the ability to manage and share a Hailo device between \
               multiple processes, thus providing the ability to use multi-process inference."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=48b1c947c88868c23e4fb874890be6fc \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=6bb4065ba26c9cc3e0761bfefbd6fa27"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=master"
SRCREV = "3d673252095c11a99147a6e72b4c11150ebd0882"

S = "${WORKDIR}/git"

inherit hailort-base systemd

SYSTEMD_SERVICE:${PN} = "hailort.service"
RDEPENDS:${PN} += "libhailort"
OECMAKE_TARGET_COMPILE = "hailort_service"

do_install:append() {
  install -d ${D}${bindir}
  install -m 0755 ${BIN_SRC_DIR}hailort_service ${D}${bindir}

  install -d ${D}${systemd_system_unitdir}
  cp ${S}/hailort/hailort_service/hailort.service ${D}${systemd_system_unitdir}

  install -d ${D}${sysconfdir}/default
  cp ${S}/hailort/hailort_service/hailort_service ${D}${sysconfdir}/default

  sed -i 's#/usr/local/bin#${bindir}#g' ${D}${systemd_system_unitdir}/hailort.service
}

FILES:${PN} += "${bindir}/hailort_service ${systemd_system_unitdir}/hailort.service ${sysconfdir}/default/hailort_service"
