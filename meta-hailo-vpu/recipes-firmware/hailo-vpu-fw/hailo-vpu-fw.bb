DESCRIPTION = "Hailo VPU. \
               This recipe copy the already compiled VPU firmware from platform-sw to image"

LICENSE = "CLOSED"
# TODO: uncomment when we have way to release the VPU-FW 
# BASE_URI = "Path to AWS?"
FW = "vpu_fw.bin"
#SRC_URI = "${BASE_URI}/${FW}"
FW_PATH = "${S}/${FW}"

FIRMWARE_INSTALL_DIR = "/lib/firmware/hailo"
ROOTFS_FIRMWARE_DIR = "${D}${FIRMWARE_INSTALL_DIR}"

do_install:append() {
  install -d ${ROOTFS_FIRMWARE_DIR}
  install -m 0644 ${FW_PATH} ${ROOTFS_FIRMWARE_DIR}
}

FILES:${PN} += "/lib /lib/firmware ${FIRMWARE_INSTALL_DIR} ${FIRMWARE_INSTALL_DIR}/*"