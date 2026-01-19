DESCRIPTION = "hailortcli - command line utility wrapper for libhailort operations, including inference \
               fw controls, measurments and more."


LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=ed57bbf10be0c74ecf2c80710208b2b3 \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=bdbc7f982b701bd5c908f8d9ad7f629e"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=hailo8"
SRCREV = "63adffec12da88f1e7cfdc07f2e07681671e83e8"

SRC_URI += " \
	file://0001-Use-a-pre-built-protobuf-dependency.patch \
	file://0002-Use-a-pre-built-spdlog-dependency.patch \
	file://0003-Use-a-pre-built-cli11-dependency.patch \
	file://0004-Use-a-pre-built-xxhash-dependency.patch \
	file://0005-Use-a-pre-built-eigen-dependency.patch \
	file://0006-Use-a-pre-built-nlohmann-json-dependency.patch \
	file://0007-Use-a-pre-built-dotwriter-dependency.patch \
	file://0008-Use-a-pre-built-readerwriterqueue-dependency.patch \
	file://0009-Build-parts-of-hailort-with-C-17.patch \
	file://0010-Link-hailortcli-with-libabsl-parts.patch \
	file://0013-Use-a-pre-built-grpc-dependency.patch \
"

inherit hailort-base

DEPENDS += " \
	protobuf-native protobuf libeigen cli11 nlohmann-json spdlog xxhash \
	readerwriterqueue dotwriter \
	${@ 'grpc-native grpc' if d.getVar('HAILO_BUILD_SERVICE') != '0' else ''} \
"

RDEPENDS:${PN} += "libhailort"
OECMAKE_TARGET_COMPILE = "hailortcli"

# Building against newer abseil-cpp versions require -fpermissive
# due to this and similar errors:
#      | .../libhailort/4.23.0/recipe-sysroot/usr/include/absl/log/internal/log_message.h:256:8:
#      | error: invalid conversion from 'const char*' to 'int' [-fpermissive]
#  256 |   void CopyToEncodedBuffer(absl::string_view str) ABSL_ATTRIBUTE_NOINLINE;
#      |        ^~~~~~~~~~~~~~~~~~~
#      |        |
#      |        const char*
CXXFLAGS += "-fpermissive"

# Skip cmake do_install process - overrides cmake bbclass
cmake_do_install() {
  :
}

do_install:append() {
  install -d ${D}${bindir}
  install -m 0755 ${BIN_SRC_DIR}/hailortcli ${D}${bindir}
}

FILES:${PN} += "${bindir}/hailortcli"
