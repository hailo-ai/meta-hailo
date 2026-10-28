DESCRIPTION = "hailortcli - command line utility wrapper for libhailort operations, including inference \
               fw controls, measurments and more."


LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=800c77403398cedcbbbcd86d37f5e0ff \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=26d65d93a121dbee87637eb4a4be8376"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=master"
SRCREV = "e7727f7482477e448f4c93b33dc62f9e5a30c3e7"

S = "${WORKDIR}/git"

inherit hailort-base

RDEPENDS:${PN} += "libhailort"
OECMAKE_TARGET_COMPILE = "hailortcli"

do_install:append() {
  install -d ${D}${bindir}
  install -m 0755 ${BIN_SRC_DIR}/hailortcli ${D}${bindir}
}

FILES:${PN} += "${bindir}/hailortcli"
