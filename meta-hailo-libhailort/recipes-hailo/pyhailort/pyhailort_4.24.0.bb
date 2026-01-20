DESCRIPTION = "pyhailort - hailo's python API \
               the recipe installed using pyhailort setuptools into python/site-packages" 

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://../../../../LICENSE;md5=ed57bbf10be0c74ecf2c80710208b2b3 \
                    file://../../../../LICENSE-3RD-PARTY.md;md5=bdbc7f982b701bd5c908f8d9ad7f629e"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=hailo8"
SRCREV = "63adffec12da88f1e7cfdc07f2e07681671e83e8"

SRC_URI += " \
    file://0001-Use-a-pre-built-pybind11-dependency.patch;patchdir=${S}/../../../../.. \
    file://0002-Set-CMAKE_VERBOSE_MAKEFILE-to-ON.patch;patchdir=${S}/../../../../.. \
    file://0003-Handle-more-settings-in-optional-cmake-arg-list.patch;patchdir=${S}/../../../../.. \
"

S .= "/hailort/libhailort/bindings/python/platform"

inherit hailort-base python3native setuptools3

DEPENDS += "python3-wheel-native libhailort python3-pybind11 git-native"
RDEPENDS:${PN} += "libhailort python3-future python3-importlib-metadata python3-netifaces \
                   python3-appdirs python3-contextlib2 python3-netaddr \
                   python3-argcomplete python3-numpy python3-setuptools"

do_compile:prepend() {
    # these cmake params should have been propagated directly to cmake. However, we inherit setuptools3 and setup.py
    # is responsible for cmake execution. These params must pass to setup.py the ENV variable, and from there, pass to
    # cmake.

    # allow linkage against HailoRT
    export HailoRT_DIR=${STAGING_LIBDIR}/cmake/HailoRT
    export HAILORT_INCLUDE_DIR="${STAGING_INCDIR}"
    export LIBHAILORT_PATH="${STAGING_LIBDIR}/libhailort.so"
    # allow linkage against pybind11
    export PYTHON_INCLUDE_DIRS=${STAGING_INCDIR}/python${PYTHON_BASEVERSION}
    export Python_INCLUDE_DIR="${PYTHON_INCLUDE_DIR}"
    export Python_LIBRARY="${PYTHON_LIBRARY}"
    # define the toolchain file
    export CMAKE_TOOLCHAIN_FILE=${WORKDIR}/toolchain.cmake
    # Fix warning with cmake 4.x
    export CMAKE_POLICY_VERSION_MINIMUM="3.10"
    # Point to the pybind11 locations
    export PYBIND11_FINDPYTHON="ON"
    export PYBIND11_PYTHON_VERSION="${PYTHON_BASEVERSION}"
    export _PYBIND11_CROSSCOMPILING="ON"
}

# prevents the following error:
# pyhailort-... do_package: QA Issue: File '..._pyhailort...so' from pyhailort was already stripped, this will prevent future debugging! [already-stripped]
INSANE_SKIP:${PN} += "already-stripped"
