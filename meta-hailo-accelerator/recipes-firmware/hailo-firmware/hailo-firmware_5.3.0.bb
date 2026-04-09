DESCRIPTION = "hailo firmware \
			   hailo10 chip firmware (hailo10_fw.tar.gz) \
			   the recipe copies the file to /lib/firmware/hailo/hailo10h on the target device’s root file system"

BASE_URI = "https://hailo-hailort.s3.eu-west-2.amazonaws.com"
FW_AWS_DIR = "Hailo10H/${PV}/FW"
FW = "hailo10h_fw.tar.gz"
LICENSE_FILE = "LICENSE"
SRC_URI = "${BASE_URI}/${FW_AWS_DIR}/${FW};md5sum=a6eb960bb021ce965a43c2cf2aa7041a \
		${BASE_URI}/${FW_AWS_DIR}/${LICENSE_FILE};md5sum=263ee034adc02556d59ab1ebdaea2cda"

LICENSE = "LICENSE"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${LICENSE_FILE};md5=263ee034adc02556d59ab1ebdaea2cda"

FW_PATH = "${WORKDIR}/${FW}"

do_install() {
	# Extract firmware (hailo10_fw.tar.gz) into the rootfs under /lib/firmware/hailo/hailo10h
	install -d ${D}/lib/firmware/hailo/hailo10h
	tar -xzf ${FW_PATH} -C ${D}/lib/firmware/hailo/hailo10h
}

FILES:${PN} += "/lib /lib/* /lib/firmware/hailo/hailo10h/*"
