require recipes-bsp/u-boot/u-boot.inc

# SPL build
UBOOT_BINARY = "u-boot-dtb.bin"
UBOOT_IMAGE = "u-boot-${MACHINE}-${PV}-${PR}.img"
UBOOT_SYMLINK = "u-boot-${MACHINE}.img"

PV = "v2014.01+v2014.04-rc3"

SRC_URI = "\
    git://git.denx.de/u-boot.git                                           \
    file://0001-arndale-enabled-bootz-command.patch                        \
    file://0002-Patch-to-get-the-arndale-board-booting-using-the-met.patch \       
"

# U-Boot patches for GCCv5 support
SRC_URI += "\
    file://gcc5/0001-Add-linux-compiler-gcc5.h-to-fix-builds-with-gcc5.patch \
    file://gcc5/0002-ARM-asm-io.h-use-static-inline.patch                    \
    file://gcc5/0003-common-main.c-make-show_boot_progress-__weak.patch      \
    file://gcc5/0004-common-board_f-cosmetic-use-__weak-for-leds.patch       \
"

# v2014.04-rc3 release tag
SRCREV = "c494eaf409cb8db9a5a513e9bdfac20b7a83daca"

LIC_FILES_CHKSUM = "file://README;beginline=1;endline=22;md5=2687c5ebfd9cb284491c3204b726ea29"

S = "${WORKDIR}/git"

