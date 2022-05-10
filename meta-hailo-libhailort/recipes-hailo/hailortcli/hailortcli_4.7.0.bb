DESCRIPTION = "hailortcli - command line utility wrapper for libhailort operations, including inference \
               fw controls, measurments and more."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=48b1c947c88868c23e4fb874890be6fc \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=330ab01bc49485a65979d8c28daee4b0"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=develop"
SRCREV = "dd37bf9936429e4cc51225ca116eaf8d84441fd7"

S = "${WORKDIR}/git"

inherit hailort-base

RDEPENDS_${PN} += "libhailort"
OECMAKE_TARGET_COMPILE = "hailortcli"

do_install_append() {
  install -d ${D}${bindir}
  install -m 0755 ${BIN_SRC_DIR}/hailortcli ${D}${bindir}
}

FILES_${PN} += "${bindir}/hailortcli"
