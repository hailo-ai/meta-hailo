DESCRIPTION = "hailortcli - command line utility wrapper for libhailort operations, including inference \
               fw controls, measurments and more."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=48b1c947c88868c23e4fb874890be6fc \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=6bb4065ba26c9cc3e0761bfefbd6fa27"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=master"
SRCREV = "5c346eddad1acc2d6b6a7cf661760279f63d274e"

S = "${WORKDIR}/git"

inherit hailort-base

RDEPENDS:${PN} += "libhailort"
OECMAKE_TARGET_COMPILE = "hailortcli"

do_install:append() {
  install -d ${D}${bindir}
  install -m 0755 ${BIN_SRC_DIR}/hailortcli ${D}${bindir}
}

FILES:${PN} += "${bindir}/hailortcli"
