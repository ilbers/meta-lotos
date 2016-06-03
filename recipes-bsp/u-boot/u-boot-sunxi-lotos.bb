# U-Boot bootloader
#
# This package is a part of LotOS framework.
#
# Copyright (c) 2015 ilbers GmbH

DESCRIPTION = "U-Boot port for LotOS framework"

require recipes-bsp/u-boot/u-boot.inc

LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "\
file://Licenses/Exceptions;md5=338a7cb1e52d0d1951f83e15319a3fe7 \
file://Licenses/bsd-2-clause.txt;md5=6a31f076f5773aabd8ff86191ad6fdd5 \
file://Licenses/bsd-3-clause.txt;md5=4a1190eac56a9db675d58ebe86eaf50c \
file://Licenses/eCos-2.0.txt;md5=b338cb12196b5175acd3aa63b0a0805c \
file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
file://Licenses/ibm-pibs.txt;md5=c49502a55e35e0a8a1dc271d944d6dba \
file://Licenses/lgpl-2.0.txt;md5=5f30f0716dfdd0d91eb439ebec522ec2 \
file://Licenses/lgpl-2.1.txt;md5=4fbd65380cdd255951079008b364516c \
"

COMPATIBLE_MACHINE = "(sun4i|sun5i|sun7i)"

# Linux-sunxi U-Boot
SRC_URI = "\
    git://github.com/ilbers/u-boot.git;protocol=git;branch=sunxi             \
    file://boot.cmd                                                          \
    file://0001-sunxi-boot-second-core-in-hyp-mode.patch                     \
    file://0002-bananapi-fix-GMAC-support.patch                              \
"

# U-Boot patches for GCCv5 support
SRC_URI += "\
    file://gcc5/0001-Add-linux-compiler-gcc5.h-to-fix-builds-with-gcc5.patch \
    file://gcc5/0002-ARM-asm-io.h-use-static-inline.patch                    \
    file://gcc5/0003-common-main.c-make-show_boot_progress-__weak.patch      \
    file://gcc5/0004-common-board_f-cosmetic-use-__weak-for-leds.patch       \
"

SRCREV = "e8487047bb6fafef477575573fded8f91f30a7d5"

PV = "v2014.04+git${SRCPV}"
PE = "1"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SPL_BINARY="u-boot-sunxi-with-spl.bin"

UBOOT_ENV_SUFFIX = "scr"
UBOOT_ENV = "boot"

do_compile_append() {
    ${S}/tools/mkimage -C none -A arm -T script -d ${WORKDIR}/boot.cmd ${WORKDIR}/${UBOOT_ENV_BINARY}
}
