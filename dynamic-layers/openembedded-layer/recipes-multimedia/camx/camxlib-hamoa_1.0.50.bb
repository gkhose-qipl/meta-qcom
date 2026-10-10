PLATFORM = "hamoa"
PBT_BUILD_DATE = "260928"

require common.inc

SRC_URI[camxlib.sha256sum] = "14e96d38272c4d63b0be0b5d09a2fcd1e1c21a4e942df8032bcb59ab6cb183cc"
SRC_URI[camx.sha256sum] = "4c4f2c4ee05afe17f453429a5ce21d65f7c9ab2cdd563cfb388c02ffb28d8f94"
SRC_URI[chicdk.sha256sum] = "f86147e244f1290f67017a1ae990c76c385c5a7e143cfeb1ed96438ca3a28939"
SRC_URI[camxcommon.sha256sum] = "311be3ca35b64b1de9ebedff984f931f0ddb8b7f921e441bbb82af8759150f19"
SRC_URI[camxtest.sha256sum] = "c572082d624e0fc5035000bc75b4a77ce05fadb47a712a13d200352ce62f1f20"

do_install:append() {
    # copy skel file
    install -d ${D}${datadir}/qcom
    cp -r ${S}/usr/share/qcom/x1e80100 ${D}${datadir}/qcom/
}
PACKAGE_BEFORE_PN += "${PN}-skel"
RDEPENDS:${PN} += "${PN}-skel"
FILES:${PN}-skel = "${datadir}/qcom"
# Algo librarires are pre-compiled, pre-stripped.
# Skipping QA checks: 'already-stripped', 'arch', 'libdir' because:
# - Library files are Pre-stripped  (already-stripped)
# - skel binaries/library are not AArch64 (arch mismatch)      (arch)
# - Files are installed under /usr/share (non-libdir path) (libdir)
# - .so symlink is used for runtime DSP usage, not a dev artifact (dev-so)
INSANE_SKIP:${PN}-skel += " arch libdir already-stripped dev-so"

# Preserve ${PN}-skel naming to avoid ambiguity in package identification.
DEBIAN_NOAUTONAME:${PN}-skel = "1"
