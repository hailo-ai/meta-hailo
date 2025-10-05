DESCRIPTION = "hailo firmware \
				hailo8 chip firmware (hailo_fw.bin) \
				the recipe copies the file to /lib/firmware/hailo/ on the target device’s root file system"

BASE_URI = "https://hailo-hailort.s3.eu-west-2.amazonaws.com"
FW_AWS_DIR = "Hailo8/${PV}/FW"
FW = "hailo8_fw.${PV}.bin"
LICENSE_FILE = "LICENSE"
SRC_URI = "${BASE_URI}/${FW_AWS_DIR}/${FW};md5sum=a9a80585a6733674e2ca158ec15971c4 \
		${BASE_URI}/${FW_AWS_DIR}/${LICENSE_FILE};md5sum=263ee034adc02556d59ab1ebdaea2cda"

LICENSE = "LICENSE"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${LICENSE_FILE};md5=263ee034adc02556d59ab1ebdaea2cda"

FW_PATH = "${WORKDIR}/hailo8_fw.${PV}.bin"

do_install() {
	# Stores hailo8_fw.bin in the rootfs under ${nonarch_base_libdir} - /usr/lib/firmware/hailo
	install -d ${D}${nonarch_base_libdir}/firmware/hailo
	install -m 0644 ${FW_PATH} ${D}${nonarch_base_libdir}/firmware/hailo/hailo8_fw.bin
}

# Package contents
FILES:${PN} += "${nonarch_base_libdir}/firmware/hailo/hailo8_fw.bin"
