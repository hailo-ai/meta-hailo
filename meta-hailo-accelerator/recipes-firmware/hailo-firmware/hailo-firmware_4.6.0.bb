DESCRIPTION = "hailo firmware 4.6.0 \
				hailo8 chip firmware (hailo_fw.bin) \
				the recipe copies the file to /lib/firmware/hailo/ on the target device’s root file system"

BASE_URI = "https://hailo-hailort.s3.eu-west-2.amazonaws.com"
FW_AWS_DIR = "Hailo8/${PV}/FW"
FW = "hailo8_fw.${PV}.bin"
LICENSE_FILE = "LICENSE"
SRC_URI = "${BASE_URI}/${FW_AWS_DIR}/${FW};md5sum=105665535a2e6c20b5b0b88f5beace13 \
		${BASE_URI}/${FW_AWS_DIR}/${LICENSE_FILE};md5sum=b523a19ec0f6ae38880ddf8d379d0f74"

LICENSE = "LICENSE"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${LICENSE_FILE};md5=b523a19ec0f6ae38880ddf8d379d0f74"

FW_PATH = "${WORKDIR}/hailo8_fw.${PV}.bin"

do_install_append() {
	# Stores hailo8_fw.bin in the rootfs under /lib/firmware/hailo
	install -d ${D}/lib/firmware/hailo
	install -m 0755 ${FW_PATH} ${D}/lib/firmware/hailo/hailo8_fw.bin
}

FILES_${PN} += "/lib /lib/* /lib/firmware/hailo/hailo8_fw*"
