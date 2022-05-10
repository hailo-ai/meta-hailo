inherit setuptools3
require python-argcomplete.inc

RDEPENDS:${PN} += "\
    ${PYTHON_PN}-argparse \
    ${PYTHON_PN}-contextlib \
"
