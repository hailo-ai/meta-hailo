SUMMARY = "Hailo RT requirements"
DESCRIPTION = "The set of packages required to enable Hailo RT functionality"

PACKAGE_ARCH = "${MACHINE_ARCH}"
# The packagegroup is just configuration/grouping mechanisms, hence tracking it in buildhistory doesn't provide useful information
BUILDHISTORY_FEATURES:remove = "image package"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS:${PN}-base = "\
    hailort-server \
    hailortcli \
    libhailort \
    "
RDEPENDS:${PN}-base-dev-pkg = "\
    ${PN}-base \
    "

ANALYSIS_PACKAGES = ""
ANALYSIS_DEV_PACKAGES = ""
RDEPENDS:${PN}-analysis = "${ANALYSIS_PACKAGES}"
RDEPENDS:${PN}-analysis-dev-pkg = "\
    ${PN}-analysis \
    ${ANALYSIS_DEV_PACKAGES}"

PCI_SERVER_PACKAGES = ""
RDEPENDS:${PN}-pci-server = "${PCI_SERVER_PACKAGES}"
RDEPENDS:${PN}-pci-server-dev-pkg = "\
    ${PN}-pci-server"

GEN_AI_DEV_PACKAGES = "\
    python3 \
    python3-numpy \
    pyhailort \
    "
RDEPENDS:${PN}-gen-ai:accelerator = ""
RDEPENDS:${PN}-gen-ai-dev-pkg:accelerator = "\
    ${PN}-gen-ai \
    ${GEN_AI_DEV_PACKAGES}"

def get_hrt_features(d):
    # Build features support DISTRO_FEATURES
    return "\
        analysis \
        gen-ai \
        pci-server \
    "

def get_packagegroups_to_install(d, packagegroup_features):
    # By default, all features are enabled
    enabledFeatures = set(packagegroup_features.split())
    if d.getVar('ENABLE_DISTRO_FEATURES') == "True":
        # If ENABLE_DISTRO_FEATURES is set, filter enabled features by DISTRO_FEATURES
        enabledFeatures = enabledFeatures.intersection(set(d.getVar('DISTRO_FEATURES').split()))
    return " ".join(f"{d.getVar('PN')}-{feature}" for feature in enabledFeatures)

def get_hrt_packagegroups_to_install(d, hrt_features):
    # Get enabled packagegroups based on features
    return get_packagegroups_to_install(d, hrt_features)

def get_hailo_hrt_packagegroups(d):
    # Build features support DISTRO_FEATURES
    hrt_features = get_hrt_features(d)

    # Get enabled packagegroups based on features
    packagegroups_to_install = get_hrt_packagegroups_to_install(d, hrt_features)

    # Add base packagegroup
    return f"{d.getVar('PN')}-base {packagegroups_to_install}"

HAILO_HRT_SUB_PACKAGEGROUPS = "${@get_hailo_hrt_packagegroups(d)}"

HAILO_HRT_DEV_SUB_PACKAGEGROUPS = "${@" ".join([sub_packagegroup + "-dev-pkg" for sub_packagegroup in d.getVar("HAILO_HRT_SUB_PACKAGEGROUPS").split()])}"

RDEPENDS:${PN} = "${HAILO_HRT_SUB_PACKAGEGROUPS}"
RDEPENDS:${PN}-dev-pkg = "${PN} ${HAILO_HRT_DEV_SUB_PACKAGEGROUPS}"

PACKAGES = "\
    ${PN} \
    ${HAILO_HRT_SUB_PACKAGEGROUPS} \
    ${PN}-dev-pkg \
    ${HAILO_HRT_DEV_SUB_PACKAGEGROUPS} \
    "
