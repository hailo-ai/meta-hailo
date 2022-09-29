DESCRIPTION = "Base class to compile libhailort component"

inherit cmake

LIB_SRC_DIR = "${WORKDIR}/lib/"
BIN_SRC_DIR = "${WORKDIR}/bin/"

OECMAKE_GENERATOR = "Unix Makefiles"
HAILORT_BUILD_TYPE = "Release"
EXTRA_OECMAKE =  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=${LIB_SRC_DIR} \
                  -DCMAKE_RUNTIME_OUTPUT_DIRECTORY=${BIN_SRC_DIR} \
                  -DCMAKE_BUILD_TYPE=${HAILORT_BUILD_TYPE}        \
                  -DCMAKE_SKIP_RPATH=ON"

EXTRA_OECMAKE:append = "-DCMAKE_EXPORT_NO_PACKAGE_REGISTRY=OFF "
EXTRA_OECMAKE:append = "-DFETCHCONTENT_FULLY_DISCONNECTED=OFF "
do_configure[network] = "1"


# Skip cmake do_install process - overrides cmake bbclass
cmake_do_install() {
    :
}
