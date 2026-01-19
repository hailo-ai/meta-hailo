DESCRIPTION = "DotWriter is a C++ API for creating DOT files for use with tools such as GraphViz."
HOMEPAGE = "https://github.com/hailo-ai/DotWriter"
LICENSE = "MIT"

inherit cmake

LIC_FILES_CHKSUM = "file://LICENSE;md5=1accdb7c0604e459642c7a408366688c"

SRC_URI = " \
	git://github.com/hailo-ai/DotWriter.git;protocol=https;branch=master \
	file://0001-Install-headers-when-using-cmake-the-same-way-as-wit.patch \
	file://0002-Build-shared-library.patch \
"

SRCREV = "e5fa8f281adca10dd342b1d32e981499b8681daf"

EXTRA_OECMAKE = "-DCMAKE_POLICY_VERSION_MINIMUM=3.5"
