IMAGE_INSTALL:append = " \
    packagegroup-hailo-integrated-nnc \
    packagegroup-hailo-hailort${@bb.utils.contains('DISTRO_FEATURES', 'hailo-dev-pkg', '-dev-pkg', '', d)} \
    "
