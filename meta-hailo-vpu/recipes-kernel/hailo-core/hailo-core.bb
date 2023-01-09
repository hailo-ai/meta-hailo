DESCRIPTION = "hailo core driver \
               compiles the kernel driver for core communication with Mercury \
               the recipe calls the compilation process with the proper cross-compiler and kernel directory. \
               the output of the compilation (hailo_core.ko) is copied to the target's rootfs"

LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891"

inherit module

# Set to real values on production
# SRC_URI = "git://git@github.com/hailo-ai/core-driver.git;protocol=ssh;branch=main"
# SRCREV = "UNSET"


EXTRA_OEMAKE += "KERNEL_DIR=${STAGING_KERNEL_DIR}"
MAKE_TARGETS = "all"
MODULES_INSTALL_TARGET = "install"

S = "${WORKDIR}/git/hailort/drivers/linux/core"
RPROVIDES:${PN} += "kernel-module-hailo-core"
