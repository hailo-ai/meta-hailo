DESCRIPTION = "hailort server - hailort server provides a client-server rpc mechanism between multiple endpoints."


LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=800c77403398cedcbbbcd86d37f5e0ff \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=6bb4065ba26c9cc3e0761bfefbd6fa27"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=master \
           https://hailo-hailort.s3.eu-west-2.amazonaws.com/hailo-hailort/CrossProducts/${PV}/tokenizers_cpp.tar.gz;name=tokenizers_cpp"
SRCREV = "5c346eddad1acc2d6b6a7cf661760279f63d274e"

SRC_URI[tokenizers_cpp.sha256sum] = "5fa87d0425174667127488dc128b27e11ada4edb1d205b1ffa8bed44b9c9fed0"

S = "${WORKDIR}/git"
etcdir = "${D}/etc"

inherit hailort-base
inherit ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}

RDEPENDS:${PN} += "libhailort bash"

SYSTEMD_SERVICE:${PN} = "hailort_server.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

EXTRA_OECMAKE:append = " -DHAILO_BUILD_HAILORT_SERVER=1 -DHAILO_BUILD_GENAI_SERVER=1"
EXTRA_OECMAKE:append = " -DTOKENIZERS_LIB_PATH=${WORKDIR}/genai_servers/libtokenizers_cpp.a"
EXTRA_OECMAKE:append = " -DTOKENIZERS_RUST_LIB_PATH=${WORKDIR}/genai_servers/libtokenizers_c.a -DTOKENIZERS_INCLUDE_DIR=${WORKDIR}/genai_servers/include"
OECMAKE_TARGET_COMPILE = "hailort_server"

do_install:append() {
  # Create /etc/default/hailort_server and append HAILO_SERVER_ADDRESS env var based on MACHINE.
  # The sysvinit script reads HAILO_SERVER_ADDRESS and passes it to the hailort_server binary.
  DEFAULTS_FILE=${etcdir}/default/hailort_server
  install -d ${etcdir}/default
  if [ "${MACHINE}" = "hailo15-sbc-gen-ai" ]; then
    echo "HAILO_SERVER_ADDRESS=0.0.0.0" >> ${DEFAULTS_FILE}
  elif echo "${MACHINE}" | grep -q "hailo15"; then
    echo "HAILO_SERVER_ADDRESS=unix-socket" >> ${DEFAULTS_FILE}
  fi

  # Install the hailort_server binary
  install -d ${D}${bindir}
  install -m 0755 ${BIN_SRC_DIR}/hailort_server ${D}${bindir}

  # Install systemd service only if systemd is enabled
  if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/hailort/hailort_server/hailort_server.service ${D}${systemd_system_unitdir}/
  else
    # Install sysvinit script (always install as fallback)
    install -d ${etcdir}/init.d
    install -d ${etcdir}/rc5.d
    install -m 0755 -D  ${S}/hailort/hailort_server/hailort_server.sh ${etcdir}/init.d
    install -m 0755 -D  ${S}/hailort/hailort_server/usb/hailort_usb_setup.sh ${D}${bindir}/
    install -m 0755 -D  ${S}/hailort/hailort_server/usb/hailort_usb_flicker.sh ${D}${bindir}/
    ln -s -r ${etcdir}/init.d/hailort_server.sh ${etcdir}/rc5.d/S20hailort_server.sh
  fi
}

FILES:${PN} += "${bindir}/hailort_server ${sysconfdir}/default/hailort_server"
