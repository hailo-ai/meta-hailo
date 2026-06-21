SUMMARY = "Hailo RT integrated NNC requirements"
DESCRIPTION = "The set of packages required to enable integrated NNC functionality"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"
PACKAGES = "packagegroup-hailo-integrated-nnc"

RDEPENDS:${PN} = "\
    hailo-integrated-nnc \
    hailo15-nnc-fw"
