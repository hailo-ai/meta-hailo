SUMMARY = "Hailo RT requirements"
DESCRIPTION = "The set of packages required to enable Hailo RT functionality"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"
PACKAGES = "packagegroup-hailo-hailort"

RDEPENDS:${PN} = "\
    hailortcli \
    libhailort"

RDEPENDS:append:${PN}:hailo10-m2 = "\
    hailo-pci-ep \
    hailort-server"

RDEPENDS:append:${PN}:hailo10-m2-devel = "\
    pyhailort \
    python3 \
    python3-numpy"
