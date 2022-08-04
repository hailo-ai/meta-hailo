DESCRIPTION = "hailo pcie driver \
               compiles the kernel driver for pci communication with hailo8 \
               the recipe calls the compilation process with the proper cross-compiler and kernel directory. \
               the output of the compilation (hailo_pci.ko) is copied to the target's rootfs"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://../../LICENSE;md5=39bba7d2cf0ba1036f2a6e2be52fe3f0"

SRC_URI = "git://git@github.com/hailo-ai/hailort-drivers.git;protocol=https;branch=develop"
SRCREV = "922436dd80661a0c2e01685662595fa5eedd77f6"

S = "${WORKDIR}/git/linux/pcie"


inherit module-base kernel-module-split linux-kernel-base
# Using poky/goarch TARGET_GOARCH - to convert architecture names
inherit goarch

MODULE_NAME = "hailo-pci"
PROVIDES = "hailo-pci"
# Installation task depends on virtual/kernel build
do_install[depends] += "virtual/kernel:do_populate_sysroot"

PKG_${PN} = "${MODULE_NAME}"
KERNEL_MODULE_AUTOLOAD += "hailo-pci"

# Kernel build directory is required for the driver compilation
KERNEL_BUILD_ARTIFACTS_DIR = "${STAGING_KERNEL_DIR}/../kernel-build-artifacts"
CFLAGS=" -O2 -pipe -g -feliminate-unused-debug-types" 

# hailo pcie driver make command
EXTRA_OEMAKE = "'CC=${CC}' 'RANLIB=${RANLIB}' 'AR=${AR}' 'AS=${AS}' 'NM=${NM}' 'TARGET_SYS=${TARGET_SYS}' 'TARGET_OS=${TARGET_OS}' 'LD=${LD}' 'ARCH=${TARGET_GOARCH}'\
 				'OECORE_TARGET_ARCH=${TARGET_ARCH}' 'OECORE_TARGET_OS=${TARGET_OS}' 'OECORE_BASELIB=lib' 'CFLAGS=${CFLAGS}' 'LDFLAGS=' 'BUILDDIR=${S}' 'KERNEL_DIR=${KERNEL_BUILD_ARTIFACTS_DIR}' all"

# KERNEL_VERSION is required for the compiled driver placement in the rootfs
KERNEL_VERSION = "${@get_kernelversion_headers('${STAGING_KERNEL_BUILDDIR}')}"
ROOTFS_PCI_DRIVER_PATH = "${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/misc"

do_install_append() {
  # Stores hailo_pci.ko in the rootfs under /lib/modules/${KERNEL_VERSION}/kernel/drivers/misc"
  install -d ${ROOTFS_PCI_DRIVER_PATH}
  install -m 0644 build/release/${TARGET_GOARCH}/hailo_pci.ko ${ROOTFS_PCI_DRIVER_PATH}
}

FILES_${PN} += " \
  /lib \
  /lib/modules \
  /lib/modules/${KERNEL_VERSION} \
  /lib/modules/${KERNEL_VERSION}/kernel \
  /lib/modules/${KERNEL_VERSION}/kernel/drivers \
  /lib/modules/${KERNEL_VERSION}/kernel/drivers/misc \
  /lib/modules/${KERNEL_VERSION}/kernel/drivers/misc/hailo_pci.ko \ "
