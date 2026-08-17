DESCRIPTION = "hailo integrated nnc driver \
               compiles the kernel driver for core communication with an integrated nnc (neural network core) \
               the recipe calls the compilation process with the proper cross-compiler and kernel directory. \
               the output of the compilation (hailo_integrated_nnc.ko) is copied to the target's rootfs"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://../../LICENSE;md5=39bba7d2cf0ba1036f2a6e2be52fe3f0"

SRC_URI = "git://git@github.com/hailo-ai/hailort-drivers.git;protocol=https;branch=master"
SRCREV = "b6dd17c609504e648eb516ff4a867167edf56f3c"

inherit module

S = "${WORKDIR}/git"

EXTRA_OEMAKE += "KERNEL_DIR=${STAGING_KERNEL_DIR}"
MAKE_TARGETS = "integrated_nnc"
MODULES_INSTALL_TARGET = "integrated_nnc install"
