DESCRIPTION = "Hailo15_NNC FW. \
               This recipe copy the already compiled Hailo15_NNC firmware to the image"

BASE_URI = "https://hailo-hailort.s3.eu-west-2.amazonaws.com"
FW_AWS_DIR = "${@d.getVar('HAILO_SOC_NAME').capitalize()}/Hailort/${PV}/FW"
FW = "${HAILO_HOST_NAME}_nnc_fw.${PV}.bin"
LICENSE_FILE = "LICENSE"
SRC_URI = "${BASE_URI}/${FW_AWS_DIR}/${FW};name=fw_${HAILO_SOC_NAME} \
		   ${BASE_URI}/${FW_AWS_DIR}/${LICENSE_FILE};md5sum=263ee034adc02556d59ab1ebdaea2cda"

SRC_URI[fw_hailo15.sha256sum] = "2d3f4a913796a119559b7bfec39b7db4a6ffacd15da3a9f6582c23b4f99c3070"
SRC_URI[fw_hailo15l.sha256sum] = "2d3f4a913796a119559b7bfec39b7db4a6ffacd15da3a9f6582c23b4f99c3070"

LICENSE = "LICENSE"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${LICENSE_FILE};md5=263ee034adc02556d59ab1ebdaea2cda"

FW_PATH = "${WORKDIR}/${FW}"

do_install() {
	# Stores hailo15_nnc_fw.bin in the rootfs under /lib/firmware/hailo
	install -d ${D}/lib/firmware/hailo
	install -m 0755 ${FW_PATH} ${D}/lib/firmware/hailo/${HAILO_HOST_NAME}_nnc_fw.bin
}

FILES:${PN} += "/lib /lib/* /lib/firmware/hailo/${HAILO_HOST_NAME}_nnc_fw*"
