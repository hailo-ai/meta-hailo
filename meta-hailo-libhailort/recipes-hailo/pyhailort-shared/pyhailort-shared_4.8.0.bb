DESCRIPTION = "pyhailort-shared - hailo’s python shared object for running inference on the hailo8 chip \
 			   the recipe compiles _pyhailort"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://hailort/LICENSE;md5=48b1c947c88868c23e4fb874890be6fc \
                    file://hailort/LICENSE-3RD-PARTY.md;md5=82a4bdb4814fa0e0e16f3eb0a75192d2"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=master"
SRCREV = "3506da8c3f1b121d103d5a9cc6458d99135c06e5"

S = "${WORKDIR}/git"

# python configuration class - exports PYTHON_LIBRARY and PYTHON_INCLUDE_DIR (mandatory for compilation)
inherit hailort-base python3native

# pyhailort build depends on protobuf, git, python3 and pybind11
DEPENDS = "protobuf python3 git-native python3-pybind11"
RDEPENDS_${PN} += "libhailort"

LIB_SRC_DIR = "${TMPDIR}/staging/lib/"
EXTRA_OECMAKE_append = "-DHAILO_BUILD_PYBIND=1 \
                        -DPYBIND11_PYTHON_VERSION=${PYTHON_BASEVERSION}"
OECMAKE_TARGET_COMPILE = "_pyhailort"