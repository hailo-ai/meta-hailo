# Include pybind11 header files in the site-packages/pybind directory to support pybind11 link compilations on the machine

PV = "2.4.3"

do_install_append() {
    cp -r ${WORKDIR}/pybind11-${PV}/include/pybind11 ${D}${libdir}/${PYTHON_DIR}/site-packages/pybind11/
}