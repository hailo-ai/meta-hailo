DESCRIPTION = "hailortcli - command line utility wrapper for libhailort operations, including inference \
               fw controls, measurments and more."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=48b1c947c88868c23e4fb874890be6fc \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=82a4bdb4814fa0e0e16f3eb0a75192d2"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=master"
SRCREV = "3506da8c3f1b121d103d5a9cc6458d99135c06e5"

S = "${WORKDIR}/git"

inherit hailort-base

RDEPENDS_${PN} += "libhailort"
OECMAKE_TARGET_COMPILE = "hailortcli"

do_install_append() {
  install -d ${D}${bindir}
  install -m 0755 ${BIN_SRC_DIR}/hailortcli ${D}${bindir}
}

FILES_${PN} += "${bindir}/hailortcli"
