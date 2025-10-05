# Disable GUI (GTK/X11) for embedded builds
PACKAGECONFIG:remove = "gtk3"
EXTRA_OECONF:append = " --enable-gui=no --without-x"
