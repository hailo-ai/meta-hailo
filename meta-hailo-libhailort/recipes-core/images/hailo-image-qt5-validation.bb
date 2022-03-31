DESCRIPTION = "Hailo extended image - based on qt5 imx-image-full"
LICENSE = "MIT"

require hailo-image-qt5.bb

IMAGE_INSTALL_append = "  \
    vim                   \
    tmux                  \
    htop                  \
"