DESCRIPTION = "Hailo15_NNC FW. \
               This recipe copy the already compiled Hailo15_NNC firmware to the image"

BASE_URI = "https://hailo-hailort.s3.eu-west-2.amazonaws.com"
FW_AWS_DIR = "${@d.getVar('HAILO_SOC_NAME').capitalize()}/Hailort/${PV}/FW"
FW = "${HAILO_HOST_NAME}_nnc_fw.${PV}.bin"
LICENSE_FILE = "LICENSE"
SRC_URI = "${BASE_URI}/${FW_AWS_DIR}/${FW};name=fw_${HAILO_SOC_NAME} \
		   ${BASE_URI}/${FW_AWS_DIR}/${LICENSE_FILE};md5sum=263ee034adc02556d59ab1ebdaea2cda"

SRC_URI[fw_hailo15.sha256sum] = "1a93394d828357e36d1ee7510710dd7e0acc5df6c7215b20c9941e8406e87dce"
SRC_URI[fw_hailo15l.sha256sum] = "69c4773de18424059954747a2e5c15194d3f0ec236d5ea350658b32f53c134a8"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"

FW_PATH = "${WORKDIR}/${FW}"

do_install() {
	# Stores hailo15_nnc_fw.bin in the rootfs under /lib/firmware/hailo
	install -d ${D}/lib/firmware/hailo
	install -m 0755 ${FW_PATH} ${D}/lib/firmware/hailo/${HAILO_HOST_NAME}_nnc_fw.bin
}

FILES:${PN} += "/lib /lib/* /lib/firmware/hailo/${HAILO_HOST_NAME}_nnc_fw*"
