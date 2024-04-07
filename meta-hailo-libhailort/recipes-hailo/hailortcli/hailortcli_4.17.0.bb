DESCRIPTION = "hailortcli - command line utility wrapper for libhailort operations, including inference \
               fw controls, measurments and more."

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=48b1c947c88868c23e4fb874890be6fc \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=f491a052559dbcdae697362cd5a13c96"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=master"
SRCREV = "a984e26af79ccf17b43adc3f1aec7ff6901badc1"

S = "${WORKDIR}/git"

inherit hailort-base

RDEPENDS_${PN} += "libhailort"
OECMAKE_TARGET_COMPILE = "hailortcli"

do_install_append() {
  install -d ${D}${bindir}
  install -m 0755 ${BIN_SRC_DIR}/hailortcli ${D}${bindir}
}

FILES_${PN} += "${bindir}/hailortcli"
