DESCRIPTION = "pyhailort - hailo's python API \
               the recipe installed using pyhailort setuptools into python/site-packages" 

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://../../../../LICENSE;md5=48b1c947c88868c23e4fb874890be6fc \
                    file://../../../../LICENSE-3RD-PARTY.md;md5=6679a4286fa6c7e5de9f32d84318ea78"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=master"
SRCREV = "3d673252095c11a99147a6e72b4c11150ebd0882"

S = "${WORKDIR}/git/hailort/libhailort/bindings/python/platform"

inherit pkgconfig hailort-base python3native setuptools3

DEPENDS += "python3-wheel-native libhailort python3-pybind11 git-native"
RDEPENDS:${PN} += "libhailort python3-future python3-importlib-metadata python3-netifaces \
                   python3-appdirs python3-contextlib2 python3-netaddr \
                   python3-argcomplete python3-verboselogs python3-numpy python3-setuptools"

do_compile:prepend() {
    # these cmake params should have been propagated directly to cmake. However, we inherit setuptools3 and setup.py
    # is responsible for cmake execution. These params must pass to setup.py the ENV variable, and from there, pass to
    # cmake.

    # allow linkage against HailoRT
    export HailoRT_DIR=${STAGING_LIBDIR}/cmake/HailoRT
    # allow linkage against pybind11
    export PYTHON_INCLUDE_DIRS=${STAGING_INCDIR}/python${PYTHON_BASEVERSION}
    # define the toolchain file
    export CMAKE_TOOLCHAIN_FILE=${WORKDIR}/toolchain.cmake
}

# prevents the following error:
# pyhailort-... do_package: QA Issue: File '..._pyhailort...so' from pyhailort was already stripped, this will prevent future debugging! [already-stripped]
INSANE_SKIP:${PN} += "already-stripped"
