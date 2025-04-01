SUMMARY = "Hailo RT requirements"
DESCRIPTION = "The set of packages required to enable Hailo RT functionality"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"
PACKAGES_BY_FEATURES = "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'hrt-gen-ai', '${PN}-gen-ai', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'hrt-server', '${PN}-pci-server', '', d)} \
    "
PACKAGES = "${PN} \
            ${PACKAGES_BY_FEATURES} \
            "

RDEPENDS:${PN} = "\
    hailortcli \
    libhailort \
    ${PACKAGES_BY_FEATURES} \
    "

PCI_SERVER_PACKAGES = ""
RDEPENDS:${PN}-pci-server = "${PCI_SERVER_PACKAGES}"

GEN_AI_PACKAGES = "pyhailort"
RDEPENDS:${PN}-gen-ai = "${GEN_AI_PACKAGES}"
