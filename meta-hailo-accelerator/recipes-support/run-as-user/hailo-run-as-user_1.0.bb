SUMMARY = "Run hailort service as user"
DESCRIPTION = "Run hailort service as user"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=39bba7d2cf0ba1036f2a6e2be52fe3f0"

inherit allarch

SRC_URI = "git://github.com/hailo-ai/hailort-drivers.git;protocol=https;branch=hailo8"
SRCREV = "ce1087bfe8132c99b41374e3128fc78612a3f492"

SRC_URI += " \
	file://hailo-systemd.conf \
	file://hailo-tmpfiles.conf \
"

do_install () {
	install -d -m0755 ${D}${sysconfdir}/udev/rules.d
	install -m0644 linux/pcie/51-hailo-udev.rules ${D}${sysconfdir}/udev/rules.d/

	install -d -m0755 ${D}${sysconfdir}/systemd/system/hailort.service.d
	sed -e 's:@HAILO_ACCEL_USER@:${HAILO_ACCEL_USER}:g' \
		-e 's:@HAILO_ACCEL_GROUP@:${HAILO_ACCEL_GROUP}:g' \
		< ${UNPACKDIR}/hailo-systemd.conf \
		> ${D}${sysconfdir}/systemd/system/hailort.service.d/run-as-user.conf

	install -d -m0755 ${D}${sysconfdir}/tmpfiles.d
	sed -e 's:@HAILO_ACCEL_USER@:${HAILO_ACCEL_USER}:g' \
		-e 's:@HAILO_ACCEL_GROUP@:${HAILO_ACCEL_GROUP}:g' \
		< ${UNPACKDIR}/hailo-tmpfiles.conf \
		> ${D}${sysconfdir}/tmpfiles.d/hailo-run-as-user.conf

	install -d -m0755 ${D}${localstatedir}/log
	install -d -m0775 -o ${HAILO_ACCEL_USER} -g ${HAILO_ACCEL_GROUP} ${D}${localstatedir}/log/hailo
}

PACKAGES = "${PN}"

FILES:${PN} += "${localstatedir}/log/hailo/"
