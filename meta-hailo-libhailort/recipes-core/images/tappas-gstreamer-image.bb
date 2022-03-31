DESCRIPTION = "Hailo gstreamer Image - based on gui image"
LICENSE = "MIT"

require recipes-fsl/images/fsl-image-gui.bb

# HailoRT dependencies
IMAGE_INSTALL_append = "  \
    hailo-firmware        \
    hailo-pci             \
    libhailort            \
"

# Gstreamer plugins
IMAGE_INSTALL_append += "                    \
    imx-gst1.0-plugin                        \
    gstreamer1.0-plugins-bad-videoparsersbad \
    gstreamer1.0-plugins-good-video4linux2   \
    gstreamer1.0-python                      \
    gstreamer1.0-plugins-base                \
    gst-shark                                \
    gst-instruments                          \
"

# Enable trace hooks for Gstreamer
PACKAGECONFIG_append_pn-gstreamer1.0 = "gst-tracer-hooks"

# Opencv requirements for the hailo gstreamer plugin’s postprocess
CORE_IMAGE_EXTRA_INSTALL += "    \
      libopencv-core-dev         \
      libopencv-highgui-dev      \
"
