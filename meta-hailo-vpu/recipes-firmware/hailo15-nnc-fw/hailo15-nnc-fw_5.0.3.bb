DESCRIPTION = "Hailo15_NNC FW. \
               This recipe copy the already compiled Hailo15_NNC firmware to the image"

BASE_URI = "https://hailo-hailort.s3.eu-west-2.amazonaws.com"
FW_AWS_DIR = "${@d.getVar('HAILO_SOC_NAME').capitalize()}/Hailort/${PV}/FW"
FW = "${HAILO_HOST_NAME}_nnc_fw.${PV}.bin"
LICENSE_FILE = "LICENSE"
SRC_URI = "${BASE_URI}/${FW_AWS_DIR}/${FW};name=fw_${HAILO_SOC_NAME} \
		   ${BASE_URI}/${FW_AWS_DIR}/${LICENSE_FILE};md5sum=263ee034adc02556d59ab1ebdaea2cda"

SRC_URI[fw_hailo15.sha256sum] = "7bf162d7530779a899bfb289467272a63fd00e94d3b50cb8c65f04f7120da8b8"
SRC_URI[fw_hailo15l.sha256sum] = "2edb7e1db6b93eaff3343ac71d967cc276eecd8e3109ec0880b4a80941c25fb1"

LICENSE = "LICENSE"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${LICENSE_FILE};md5=263ee034adc02556d59ab1ebdaea2cda"

FW_PATH = "${WORKDIR}/${FW}"

do_install() {
	# Stores hailo15_nnc_fw.bin in the rootfs under /lib/firmware/hailo
	install -d ${D}/lib/firmware/hailo
	install -m 0755 ${FW_PATH} ${D}/lib/firmware/hailo/${HAILO_HOST_NAME}_nnc_fw.bin
}

FILES:${PN} += "/lib /lib/* /lib/firmware/hailo/${HAILO_HOST_NAME}_nnc_fw*"
