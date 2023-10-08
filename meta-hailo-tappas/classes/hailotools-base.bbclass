# hailo-tools base class - setting the base configuration for meson (target, type, includes etc...)
# deppends on meta-hailo-libhailort recipes, opencv, xtensor and xtl

inherit meson pkgconfig

S = "${WORKDIR}/git/core/hailo"

DEPENDS = "libgsthailo libhailort opencv xtensor xtl"

TAPPAS_BUILD_TARGET = "all"
TAPPAS_BUILD_TYPE = "release"
PARALLEL_MAKE = "-j 4"

GST_HAILO_INCLUDE_DIR = "${STAGING_INCDIR}/gst-hailo/metadata"
HAILO_INCLUDE_DIR = "${STAGING_INCDIR}/hailort"

TARGET_PLATFORM = "imx8"
TARGET_PLATFORM:hailo15 = "hailo15"

EXTRA_OEMESON += " \
        -Dlibargs='-I${GST_HAILO_INCLUDE_DIR},-I${HAILO_INCLUDE_DIR}' \
        -Dlibxtensor='${STAGING_INCDIR}/xtensor' \
        -Dinclude_blas=false \
        -Dtarget='${TAPPAS_BUILD_TARGET}' \
        -Dtarget_platform='${TARGET_PLATFORM}' \
        -Dcpp_std='c++17' \
        --buildtype='${TAPPAS_BUILD_TYPE}' \
        "