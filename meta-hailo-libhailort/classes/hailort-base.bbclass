DESCRIPTION = "Base class to compile libhailort component"

inherit cmake pkgconfig

LIB_SRC_DIR = "${WORKDIR}/lib/"
BIN_SRC_DIR = "${WORKDIR}/bin/"

OECMAKE_GENERATOR = "Unix Makefiles"
HAILORT_BUILD_TYPE = "RelWithDebInfo"
HAILO_BUILD_SERVICE ?= "0"
EXTRA_OECMAKE =  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=${LIB_SRC_DIR} \
                  -DCMAKE_RUNTIME_OUTPUT_DIRECTORY=${BIN_SRC_DIR} \
                  -DCMAKE_BUILD_TYPE=${HAILORT_BUILD_TYPE}        \
                  -DCMAKE_SKIP_RPATH=ON                           \
                  -DHAILO_BUILD_SERVICE=${HAILO_BUILD_SERVICE}    \
                  ${@'-DHAILO_GRPC_CPP_PLUGIN_EXECUTABLE=${STAGING_BINDIR_NATIVE}/grpc_cpp_plugin' if d.getVar('HAILO_BUILD_SERVICE') != '0' else ''} \
                  "

EXTRA_OECMAKE:append = "-DCMAKE_EXPORT_NO_PACKAGE_REGISTRY=OFF "
