DESCRIPTION = "TAPPAS ARM applications recipe, including GStreamer based detection app \
				the recipe copies the app script and the hef file to /home/root/apps/detection"

PV_PARSED = "${@ '${PV}'.replace('.0', '')}"
SRC_URI = "git://git@github.com/hailo-ai/tappas.git;protocol=https;branch=master"
SRC_URI_append = " https://hailo-tappas.s3.eu-west-2.amazonaws.com/v${PV_PARSED}/arm/hefs/yolov5m_yuv.hef;md5sum=41eeee848844f65634d0873188b08ce1"
SRCREV = "820d3158c26f189ba72a8d8cfa5475418e5309d4"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM += "file://LICENSE;md5=4fbd65380cdd255951079008b364516c"

S = "${WORKDIR}/git"

RDEPENDS_${PN} += "libgsthailotools libgsthailo bash"

# Uses tappas release as an external source (set TAPPAS_EXTERNALSRC variable in your conf/local.conf to your local path)
TAPPAS_DETECTION_APP_DIR = "${S}/apps/gstreamer/arm/detection"


do_install_append() {
	# Stores in the rootfs under /home/root/apps/detection
	install -d ${D}/home/root/apps/detection

	# Copies the app bash script and the hef files
	install -m 0755 ${TAPPAS_DETECTION_APP_DIR}/detection.sh ${D}/home/root/apps/detection
	cp ${WORKDIR}/*.hef ${D}/home/root/apps/detection
}

FILES_${PN} += "/home/root/apps/* /home/root/apps/detection/*"