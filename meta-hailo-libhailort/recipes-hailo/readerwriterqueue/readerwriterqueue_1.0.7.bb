DESCRIPTION = "A fast single-producer, single-consumer lock-free queue for C++"
HOMEPAGE = "https://github.com/cameron314/readerwriterqueue"
LICENSE = "BSD-2-Clause"

inherit cmake

LIC_FILES_CHKSUM = "file://LICENSE.md;md5=ee6a511ac75d33a4520d961860f77103"

SRC_URI = "git://github.com/cameron314/readerwriterqueue.git;protocol=https;branch=master"

SRCREV = "211616e0554f93152ab3108b8d93fbc23174a9d9"
