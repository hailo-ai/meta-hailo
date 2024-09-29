DESCRIPTION = "Hailo Base Image - based on qt5 imx-image-full"
LICENSE = "MIT"

require recipes-fsl/images/fsl-image-gui.bb

IMAGE_INSTALL_append = "  \
    packagegroup-qt5-imx  \
    packagegroup-core-buildessential   \
    python3               \
    python3-pip           \
    tmux \
    htop \
    vim \
    libgpiod \
"

# HailoRT dependencies
IMAGE_INSTALL_append = " \
    hailo-firmware        \
    hailo-pci             \
    libhailort            \
    libgsthailo           \
    libgsthailotools      \
"

IMAGE_FEATUES += " \
    package-management \
"

# Gstreamer plugins
IMAGE_INSTALL_append += "                    \
    imx-gst1.0-plugin                        \
    gstreamer1.0-plugins-bad-videoparsersbad \
    gstreamer1.0-plugins-good-video4linux2   \
    gstreamer1.0-python                      \
    gstreamer1.0-plugins-base                \
    gst-instruments                          \
"

# Enable trace hooks for Gstreamer
PACKAGECONFIG_append_pn-gstreamer1.0 = "gst-tracer-hooks"
