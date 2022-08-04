DESCRIPTION = "hailortcli - command line utility wrapper for libhailort operations, including inference \
               fw controls, measurments and more."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=48b1c947c88868c23e4fb874890be6fc \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=82a4bdb4814fa0e0e16f3eb0a75192d2"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=develop"
SRCREV = "6346a2db99a5d4314a8dc42a34d9be7391b9a948"

S = "${WORKDIR}/git"

inherit hailort-base

RDEPENDS_${PN} += "libhailort"
OECMAKE_TARGET_COMPILE = "hailortcli"

do_install_append() {
  install -d ${D}${bindir}
  install -m 0755 ${BIN_SRC_DIR}/hailortcli ${D}${bindir}
}

FILES_${PN} += "${bindir}/hailortcli"
