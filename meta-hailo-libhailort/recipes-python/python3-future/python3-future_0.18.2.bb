# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Clean single-source support for Python 3 and 2"
HOMEPAGE = "https://python-future.org"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   docs/_themes/LICENSE
# NOTE: Original package / source metadata indicates license is: MIT
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a253924061f8ecc41ad7a2ba1560e8e7 \
                    file://docs/_themes/LICENSE;md5=da1f8f97f9ee64ad7466c3c531ad2c5b"

SRC_URI = "https://files.pythonhosted.org/packages/45/0b/38b06fd9b92dc2b68d58b75f900e97884c45bedd2ff83203d933cf5851c9/future-${PV}.tar.gz"
SRC_URI[md5sum] = "e4579c836b9c025872efe230f6270349"
SRC_URI[sha256sum] = "b1bead90b70cf6ec3f0710ae53a525360fa360d306a86583adc6bf83a4db537d"

S = "${WORKDIR}/future-${PV}"

inherit setuptools3

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS:${PN} += "${PYTHON_PN}-2to3 ${PYTHON_PN}-audio ${PYTHON_PN}-codecs ${PYTHON_PN}-compression ${PYTHON_PN}-contextlib2 ${PYTHON_PN}-core ${PYTHON_PN}-crypt ${PYTHON_PN}-ctypes ${PYTHON_PN}-datetime ${PYTHON_PN}-db ${PYTHON_PN}-distutils ${PYTHON_PN}-doctest ${PYTHON_PN}-email ${PYTHON_PN}-gdbm ${PYTHON_PN}-html ${PYTHON_PN}-image ${PYTHON_PN}-io  ${PYTHON_PN}-math ${PYTHON_PN}-mime ${PYTHON_PN}-misc ${PYTHON_PN}-multiprocessing ${PYTHON_PN}-netclient ${PYTHON_PN}-netserver ${PYTHON_PN}-numbers ${PYTHON_PN}-pickle ${PYTHON_PN}-pprint ${PYTHON_PN}-pydoc ${PYTHON_PN}-shell ${PYTHON_PN}-tests ${PYTHON_PN}-unittest ${PYTHON_PN}-unixadmin ${PYTHON_PN}-xml"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    Dialog
#    FileDialog
#    ScrolledText
#    SimpleDialog
#    Tix
#    Tkconstants
#    Tkdnd
#    Tkinter
#    __builtin__
#    _dummy_thread
#    _scproxy
#    _winreg
#    collections.abc
#    dbm.dumb
#    dbm.gnu
#    dbm.ndbm
#    future
#    future.backports
#    future.backports.email
#    future.backports.email._encoded_words
#    future.backports.email._parseaddr
#    future.backports.email._policybase
#    future.backports.email.charset
#    future.backports.email.encoders
#    future.backports.email.errors
#    future.backports.email.feedparser
#    future.backports.email.generator
#    future.backports.email.header
#    future.backports.email.iterators
#    future.backports.email.message
#    future.backports.email.mime.base
#    future.backports.email.mime.nonmultipart
#    future.backports.email.parser
#    future.backports.email.quoprimime
#    future.backports.email.utils
#    future.backports.html.entities
#    future.backports.http
#    future.backports.http.client
#    future.backports.http.cookiejar
#    future.backports.http.server
#    future.backports.misc
#    future.backports.test
#    future.backports.urllib
#    future.backports.urllib.parse
#    future.backports.xmlrpc.client
#    future.builtins
#    future.builtins.iterators
#    future.builtins.misc
#    future.builtins.new_min_max
#    future.builtins.newnext
#    future.builtins.newround
#    future.builtins.newsuper
#    future.moves.dbm
#    future.moves.html
#    future.moves.html.entities
#    future.moves.html.parser
#    future.moves.subprocess
#    future.moves.test
#    future.standard_library
#    future.standard_library.email._policybase
#    future.standard_library.email.headerregistry
#    future.standard_library.email.utils
#    future.types
#    future.types.newbytes
#    future.types.newdict
#    future.types.newint
#    future.types.newobject
#    future.types.newstr
#    future.utils
#    future.utils.surrogateescape
#    html
#    html.entities
#    html.parser
#    http.client
#    http.cookiejar
#    http.cookies
#    http.server
#    imp
#    libfuturize
#    libfuturize.fixer_util
#    libfuturize.fixes
#    libfuturize.fixes.fix_print
#    libfuturize.main
#    libpasteurize.fixes
#    libpasteurize.fixes.fix_division
#    libpasteurize.main
#    lzma
#    past
#    past.builtins
#    past.builtins.misc
#    past.builtins.noniterators
#    past.types
#    past.utils
#    reprlib
#    thread
#    tkColorChooser
#    tkCommonDialog
#    tkFileDialog
#    tkFont
#    tkMessageBox
#    tkinter
#    tkinter.colorchooser
#    tkinter.commondialog
#    tkinter.constants
#    tkinter.dialog
#    tkinter.dnd
#    tkinter.filedialog
#    tkinter.font
#    tkinter.messagebox
#    tkinter.scrolledtext
#    tkinter.simpledialog
#    tkinter.tix
#    tkinter.ttk
#    unittest2
#    urllib.error
#    urllib.parse
#    urllib.request
#    urllib.response
#    urllib.robotparser
#    winreg
#    xmlrpc.client
#    xmlrpc.server


