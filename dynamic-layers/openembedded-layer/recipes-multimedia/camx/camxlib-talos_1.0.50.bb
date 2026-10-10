PLATFORM = "talos"
PBT_BUILD_DATE = "260928"

require common.inc

SRC_URI[camxlib.sha256sum] = "a5c195c5a22639ee050630c886823460fa3648af378204dadbbcd3acd12e51ab"
SRC_URI[camx.sha256sum] = "2aac5872d1f67f2c3ee8fd6b38886555dc58b461cd53fdf20b590afee6cdcc26"
SRC_URI[chicdk.sha256sum] = "252bc8d204253f4e8601ea5ef26df8892b7e9b6e6a24c53c0bec51b630532e8a"
SRC_URI[camxcommon.sha256sum] = "c61962524f53101493f006063dbccab3d2290256a7409f93aba0efd092411fff"
SRC_URI[camxtest.sha256sum] = "6ef90f2dd3f74d487bc5a18b0c3d049e9761d0f5716507b2d9c0b04912928f94"

DEPENDS += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opencl', 'virtual/libopencl1', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'virtual/egl virtual/libgles2', '', d)} \
"

do_install:append() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'opengl opencl', 'false', 'true', d)}; then
        rm -f ${D}${libdir}/camx/${PLATFORM}/camera/components/libiwarp*
        rm -f ${D}${libdir}/camx/${PLATFORM}/camera/components/libhidrx*
    fi
}
