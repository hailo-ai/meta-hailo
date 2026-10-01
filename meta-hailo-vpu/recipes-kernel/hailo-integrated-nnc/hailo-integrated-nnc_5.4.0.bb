DESCRIPTION = "hailo integrated nnc driver \
               compiles the kernel driver for core communication with an integrated nnc (neural network core) \
               the recipe calls the compilation process with the proper cross-compiler and kernel directory. \
               the output of the compilation (hailo_integrated_nnc.ko) is copied to the target's rootfs"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://../../LICENSE;md5=39bba7d2cf0ba1036f2a6e2be52fe3f0"

SRC_URI = "git://git@github.com/hailo-ai/hailort-drivers.git;protocol=https;branch=master"
SRCREV = "309eb102af12a7986a8d635723b52d33c96bf7b3"

inherit module

S = "${WORKDIR}/git/linux/integrated_nnc"

# Public hailort-drivers keeps common/include at the repo root, so the Kbuild default points outside the repo
EXTRA_OEMAKE += "KERNEL_DIR=${STAGING_KERNEL_DIR} COMMON_INCLUDE_DIRECTORY=../../common/include"
MAKE_TARGETS = "all"
MODULES_INSTALL_TARGET = "install"