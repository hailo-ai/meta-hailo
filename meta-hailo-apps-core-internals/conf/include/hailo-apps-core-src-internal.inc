INC_FILE = "${@'hailo-apps-core-bitbucket-src' if d.getVar('HAILO_APPS_CORE_BITBUCKET_BRANCH') is not None else 'hailo-apps-core-external-src'}"
require conf/include/${INC_FILE}.inc
