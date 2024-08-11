DESCRIPTION = "hailort service - The Multi-Process Service enables the ability to manage and share a Hailo device between \
               multiple processes, thus providing the ability to use multi-process inference."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=48b1c947c88868c23e4fb874890be6fc \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=0b218cdfba3046481fb21963f50be29a"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=master"
SRCREV = "5c346eddad1acc2d6b6a7cf661760279f63d274e"

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
