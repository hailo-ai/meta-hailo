DESCRIPTION = "Bash tab completion for argparse"
AUTHOR = "Andrey Kislyuk"
HOMEPAGE = "https://github.com/kislyuk/argcomplete"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=2ee41112a44fe7014dce33e26468ba93"

PR = "r1"

SRC_NAME = "argcomplete"

SRC_URI = "https://files.pythonhosted.org/packages/cb/53/d2e3d11726367351b00c8f078a96dacb7f57aef2aca0d3b6c437afc56b55/argcomplete-1.12.2.tar.gz"
SRC_URI[md5sum] = "7996abf64b8599661676c91355225e05"
SRC_URI[sha256sum] = "de0e1282330940d52ea92a80fea2e4b9e0da1932aaa570f84d268939d1897b04"

S = "${WORKDIR}/argcomplete-${PV}"

BBCLASSEXTEND = "native nativesdk"
