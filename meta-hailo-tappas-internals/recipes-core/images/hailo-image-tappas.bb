DESCRIPTION = "Hailo Arm-Tappas Image - based on hailo qt5 image"
LICENSE = "MIT"

require hailo-image-qt5.bb

# Arm-Tappas dependencies
IMAGE_INSTALL_append = "  \
    python3-pycairo       \
    python3-psutil        \
    python3-pygobject     \
    python3-cython        \
    python3-pybind11      \
    python3-pillow        \
    python3-scipy         \
    librsvg               \
    gtk+3                 \
"

# Gstreamer plugins
IMAGE_INSTALL_append += "                    \
    imx-gst1.0-plugin                        \
    gstreamer1.0-plugins-bad-videoparsersbad \
    gstreamer1.0-plugins-ugly                \
    gstreamer1.0-plugins-good-video4linux2   \
"

CORE_IMAGE_EXTRA_INSTALL += "    \
      libopencv-core-dev         \
      libopencv-highgui-dev      \
      libopencv-imgproc-dev      \
      libopencv-objdetect-dev    \
      libopencv-ml-dev           \
      imx-gpu-viv                \
"
