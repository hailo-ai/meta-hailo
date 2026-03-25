DESCRIPTION = "hailortcli - command line utility wrapper for libhailort operations, including inference \
               fw controls, measurments and more."


LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=800c77403398cedcbbbcd86d37f5e0ff \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=eb78bffb175a3f2be317bb4c45fedecf"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=master-v5.3.0"
SRCREV = "3ad6b763a9ad671a90bc8b167872605378558f97"

S = "${WORKDIR}/git"

inherit hailort-base

RDEPENDS:${PN} += "libhailort"
OECMAKE_TARGET_COMPILE = "hailortcli"

do_install:append() {
  install -d ${D}${bindir}
  install -m 0755 ${BIN_SRC_DIR}/hailortcli ${D}${bindir}
}

FILES:${PN} += "${bindir}/hailortcli"
