INC_FILE = "${@'tappas-bitbucket-src' if d.getVar('TAPPAS_BITBUCKET_BRANCH') is not None else 'tappas-external-src'}"
require conf/include/${INC_FILE}.inc
