FILESEXTRAPATHS:prepend:hailo15 := "${THISDIR}/files/:"

SRC_URI:append:hailo15 = "file://appsink-add-propose_allocation-support.patch;striplevel=3"