DESCRIPTION = "hailo firmware \
				hailo8 chip firmware (hailo_fw.bin) \
				the recipe copies the file to /lib/firmware/hailo/ on the target device’s root file system"

BASE_URI = "https://hailo-hailort.s3.eu-west-2.amazonaws.com"
FW_AWS_DIR = "Hailo8/${PV}/FW"
FW = "hailo8_fw.${PV}.bin"
FW_DST = "hailo8_fw.bin"
LICENSE_FILE = "LICENSE"
SRC_URI = "${BASE_URI}/${FW_AWS_DIR}/${FW};md5sum=e8dc953946a5bf47ca8554e78f25bb11 \
		${BASE_URI}/${FW_AWS_DIR}/${LICENSE_FILE};md5sum=263ee034adc02556d59ab1ebdaea2cda"

inherit allarch

LICENSE = "LICENSE"
LIC_FILES_CHKSUM = "file://${LICENSE_FILE};md5=263ee034adc02556d59ab1ebdaea2cda"

S = "${UNPACKDIR}"

FW_PATH = "${S}/${FW}"
FW_DST_DIR = "${nonarch_base_libdir}/firmware/hailo"
FW_DST_PATH = "${FW_DST_DIR}/${FW_DST}"

do_install() {
	install -d ${D}${FW_DST_DIR}
	install -m 0644 ${FW_PATH} ${D}${FW_DST_PATH}
}

# Package contents
FILES:${PN} += "${FW_DST_PATH}"
