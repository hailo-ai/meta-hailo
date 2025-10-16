DESCRIPTION = "hailort server - hailort server provides a client-server rpc mechanism between multiple endpoints."


LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=ed57bbf10be0c74ecf2c80710208b2b3 \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=52e1117309dfa1127f21647483138f20"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=master \
           https://hailo-hailort.s3.eu-west-2.amazonaws.com/CrossProducts/${PV}/tokenizers_cpp.tar.gz;name=tokenizers_cpp"
SRCREV = "3b0e7a72d291299b6d3447333d920e6688060f71"

SRC_URI[tokenizers_cpp.sha256sum] = "a5bcc287ded1d5b871ff4f5f69d845641cf00fe9758a42a5022f9ca21c6860f8"

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

HAILO_SERVER_ADDRESS = "unix-socket"
HAILO_SERVER_ADDRESS:vpu = "unix-socket"
HAILO_SERVER_ADDRESS:accelerator = "${@'0.0.0.0' if d.getVar('MACHINE') == 'hailo15-sbc-gen-ai' else ''}"

do_install:append() {
  # Create /etc/default/hailort_server and append HAILO_SERVER_ADDRESS env var.
  # The sysvinit script reads HAILO_SERVER_ADDRESS and passes it to the hailort_server binary.
  if [ -n "${HAILO_SERVER_ADDRESS}" ]; then
    DEFAULTS_FILE=${etcdir}/default/hailort_server
    install -d ${etcdir}/default
    echo "HAILO_SERVER_ADDRESS=${HAILO_SERVER_ADDRESS}" >> ${DEFAULTS_FILE}
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
    ln -s -r ${etcdir}/init.d/hailort_server.sh ${etcdir}/rc5.d/S20hailort_server.sh
  fi
}

FILES:${PN} += "${bindir}/hailort_server"
FILES:${PN} += "${@'${sysconfdir}/default/hailort_server' if d.getVar('HAILO_SERVER_ADDRESS') else ''}"
