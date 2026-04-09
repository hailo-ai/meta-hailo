DESCRIPTION = "hailo pcie driver \
               compiles the kernel driver for pci communication with hailo8 \
               the recipe calls the compilation process with the proper cross-compiler and kernel directory. \
               the output of the compilation (hailo_pci.ko) is copied to the target's rootfs"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://../../LICENSE;md5=39bba7d2cf0ba1036f2a6e2be52fe3f0"

SRC_URI = "git://git@github.com/hailo-ai/hailort-drivers.git;protocol=https;branch=master-v5.3.0-hotfix"
SRCREV = "14daea4d542ba1973087a2c062c7228255002106"

inherit module

S = "${WORKDIR}/git/linux/pcie"

EXTRA_OEMAKE += "KERNEL_DIR=${STAGING_KERNEL_DIR}"
MAKE_TARGETS = "all"
MODULES_INSTALL_TARGET = "install"