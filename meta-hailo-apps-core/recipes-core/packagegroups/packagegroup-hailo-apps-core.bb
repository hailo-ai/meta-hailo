SUMMARY = "Hailo-Apps Core requirements"
DESCRIPTION = "The minimal set of packages required to boot the system"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"
PACKAGES = "\
            packagegroup-hailo-apps-core \
            packagegroup-hailo-apps-core-dev-pkg"

RDEPENDS:${PN} = "\
    hailo-post-processes \
    libgsthailo \
    libgsthailotools"

RDEPENDS:${PN}-dev-pkg = "\
    packagegroup-hailo-apps-core \
    hailo-apps-core-tracers \
    opencv \
        "
