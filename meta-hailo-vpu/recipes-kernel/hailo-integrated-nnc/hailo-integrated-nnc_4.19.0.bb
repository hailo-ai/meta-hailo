DESCRIPTION = "hailo integrated nnc driver \
               compiles the kernel driver for core communication with an integrated nnc (neural network core) \
               the recipe calls the compilation process with the proper cross-compiler and kernel directory. \
               the output of the compilation (hailo_integrated_nnc.ko) is copied to the target's rootfs"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://../../LICENSE;md5=39bba7d2cf0ba1036f2a6e2be52fe3f0"

SRC_URI = "git://git@github.com/hailo-ai/hailort-drivers.git;protocol=https;branch=master"
SRCREV = "7161f9ee5918029bd4497f590003c2f87ec32507"

inherit module

S = "${WORKDIR}/git/linux/integrated_nnc"

EXTRA_OEMAKE += "KERNEL_DIR=${STAGING_KERNEL_DIR}"
MAKE_TARGETS = "all"
MODULES_INSTALL_TARGET = "install"