DESCRIPTION = "hailort server - hailort server provides a client-server rpc mechanism between multiple endpoints."


LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=48b1c947c88868c23e4fb874890be6fc \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=6bb4065ba26c9cc3e0761bfefbd6fa27"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=master"
SRCREV = "5c346eddad1acc2d6b6a7cf661760279f63d274e"

S = "${WORKDIR}/git"
etcdir = "${D}/etc"

inherit hailort-base

RDEPENDS:${PN} += "libhailort"
RDEPENDS:${PN} += "bash"
OECMAKE_TARGET_COMPILE = "hailort_server"

do_install:append() {
  install -d ${etcdir}
  install -d ${etcdir}/init.d
  install -d ${etcdir}/rc5.d
  install -d ${D}${bindir}

  install -m 0755 -D  ${S}/hailort/hailort_server/hailort_server.sh ${etcdir}/init.d

  install -m 0755 ${BIN_SRC_DIR}/hailort_server ${D}${bindir}
  ln -s -r ${etcdir}/init.d/hailort_server.sh ${etcdir}/rc5.d/S20hailort_server.sh
}

FILES:${PN} += "${bindir}/hailort_server"
