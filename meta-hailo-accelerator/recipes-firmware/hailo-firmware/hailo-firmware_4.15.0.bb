DESCRIPTION = "hailo firmware \
				hailo8 chip firmware (hailo_fw.bin) \
				the recipe copies the file to /lib/firmware/hailo/ on the target device’s root file system"

LICENSE = "CLOSED"
LICENSE_FILE = "LICENSE"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${LICENSE_FILE};md5=263ee034adc02556d59ab1ebdaea2cda"

BASE_URI = "https://hailo-hailort.s3.eu-west-2.amazonaws.com"
FW_AWS_DIR = "Hailo8/${PV}/FW"
FW = "hailo8_fw.${PV}.bin"
SRC_URI = "${BASE_URI}/${FW_AWS_DIR}/${FW};md5sum=2622de442a92149f60da846906490a8c \
		${BASE_URI}/${FW_AWS_DIR}/${LICENSE_FILE};md5sum=263ee034adc02556d59ab1ebdaea2cda"

FW_PATH = "${WORKDIR}/hailo8_fw.${PV}.bin"

do_install() {
	# Stores hailo8_fw.bin in the rootfs under /lib/firmware/hailo
	install -d ${D}/lib/firmware/hailo
	install -m 0755 ${FW_PATH} ${D}/lib/firmware/hailo/hailo8_fw.bin
}

FILES:${PN} += "/lib /lib/* /lib/firmware/hailo/hailo8_fw*"
