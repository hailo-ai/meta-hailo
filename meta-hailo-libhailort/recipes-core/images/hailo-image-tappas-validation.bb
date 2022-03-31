DESCRIPTION = "Hailo Arm-Tappas Image - based on hailo qt5 image"
LICENSE = "MIT"

require hailo-image-qt5-validation.bb
require hailo-image-tappas.bb

# Arm-Tappas dependencies
IMAGE_INSTALL_append = "  \
    python3-matplotlib    \
"
