# U-Boot bootloader for Banana Pi board
#
# This package is a part of LotOS framework.
#
# Copyright (c) 2015-2016 ilbers GmbH

require recipes-bsp/u-boot/u-boot.inc

COMPATIBLE_MACHINE = "(sun7i)"
PACKAGE_ARCH = "${MACHINE_ARCH}"
SPL_BINARY="u-boot-sunxi-with-spl.bin"

PV = "v2016.03"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"
SRC_URI = "\
    git://git.denx.de/u-boot.git;tag=${PV}  \
    file://boot.cmd                            \
    file://0001-sunxi-boot-secondary-CPU.patch \
"

# The following option provides compatibility with gcc version 5.
EXTRA_OEMAKE_append = " KCFLAGS=-fgnu89-inline"

S = "${WORKDIR}/git"


UBOOT_ENV_SUFFIX = "scr"
UBOOT_ENV = "boot"

do_compile_append() {
    ${S}/tools/mkimage -C none -A arm -T script -d ${WORKDIR}/boot.cmd ${WORKDIR}/${UBOOT_ENV_BINARY}
}
