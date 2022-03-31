DESCRIPTION = "Hailo Base Image - based on qt5 imx-image-full"
LICENSE = "MIT"

require recipes-fsl/images/imx-image-core.bb

IMAGE_INSTALL_append = "  \
    packagegroup-qt5-imx  \
    packagegroup-core-buildessential   \
    python3               \
    python3-pip           \
"

# HailoRT dependencies
IMAGE_INSTALL_append = " \
    hailo-firmware        \
    hailo-pci             \
    libhailort            \
    pyhailort             \
"

IMAGE_FEATUES += " \
    package-management \
"
