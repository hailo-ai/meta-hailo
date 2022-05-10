# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Fast, Extensible Progress Meter"
HOMEPAGE = "https://github.com/tqdm/tqdm"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENCE
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "MIT & MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENCE;md5=054ef7105cba050017d1368262a1623c"

SRC_URI = "https://files.pythonhosted.org/packages/cd/22/f15bb3c0a2810da0e825d193fb76d782015d82c676c4f682a957814926d7/tqdm-${PV}.tar.gz"
SRC_URI[md5sum] = "aa3d5fd173c9fea7408cad14c0e73d5d"
SRC_URI[sha256sum] = "4789ccbb6fc122b5a6a85d512e4e41fc5acad77216533a6f2b8ce51e0f265c23"

S = "${WORKDIR}/tqdm-${PV}"

inherit setuptools3

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= "dev"
PACKAGECONFIG[dev] = ",,,python3-argopt python3-py-make python3-pydoc-markdown python3-twine"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS:${PN} += "python3-core"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    IPython
#    IPython.display
#    IPython.html.widgets
#    __future__
#    cgi
#    collections
#    colorama
#    contextlib
#    copy
#    ctypes
#    functools
#    html
#    io
#    ipywidgets
#    keras.callbacks
#    logging
#    matplotlib
#    matplotlib.pyplot
#    multiprocessing
#    numbers
#    ordereddict
#    os
#    pandas
#    pandas.core.frame
#    pandas.core.groupby
#    pandas.core.groupby.generic
#    pandas.core.groupby.groupby
#    pandas.core.series
#    pandas.core.window
#    pkg_resources
#    platform
#    re
#    shlex
#    shutil
#    struct
#    subprocess
#    termios
#    threading
#    warnings
#    weakref


RPROVIDES:${PN} = "tqdm"
