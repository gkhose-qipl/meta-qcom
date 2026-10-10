PLATFORM = "shikra"
PBT_BUILD_DATE = "260928"

require common.inc

SRC_URI[camxlib.sha256sum] = "c489cc217f452933a55ddc73029ef1986238ff1c794f1da4debb330d769edf5f"
SRC_URI[camx.sha256sum] = "f3f63fccb9c259fa72fbaafebe4d41fe71213c62c7d9006040cfa2fbb891d0e9"
SRC_URI[chicdk.sha256sum] = "5e1d9813a003997b6dade431b9aa354e3ec79ad35d24790b5ef19bfee7440016"
SRC_URI[camxcommon.sha256sum] = "509499da73e2b6bed95d338216048e46defd21093046033a7016e6bdde62a5a8"
SRC_URI[camxtest.sha256sum] = "1179e697034e94ca1ee447cdc716edc15e0373034987a69981d140ae562f263b"

DEPENDS += " \
    sensinghub \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opencl', 'virtual/libopencl1', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'virtual/egl virtual/libgles2', '', d)} \
"

do_install:append() {
    # Remove OpenCL-dependent libraries when opencl is not enabled.
    if ${@bb.utils.contains('DISTRO_FEATURES', 'opencl', 'false', 'true', d)}; then
        rm -f ${D}${libdir}/camx/${PLATFORM}/camera/components/com.qti.node.gpu*
    fi
}
