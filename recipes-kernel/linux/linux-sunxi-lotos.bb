# Linux kernel.
#
# This package is a part of LotOS framework.
#
# Copyright (c) 2015 ilbers GmbH

DESCRIPTION = "Linux Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel siteinfo

PV = "3.4.103"

# Upstream kernel
SRC_URI += "\
    git://github.com/LeMaker/linux-sunxi.git;branch=lemaker-3.4;protocol=git           \
    file://0001-compiler-gcc5.patch                                                    \
    file://0002-use-static-inline-in-ARM-ftrace.patch                                  \
"

# LotOS framework patches
SRC_URI += "\
    file://0001-arm-arch_timer-force-to-use-virtual-timer.patch                        \
    file://0002-sunxi-smp-disable-SMP-startup.patch                                    \
    file://0003-BananaPi-add-Mango-defconfig.patch                                     \
    file://0004-sun7i-fix-board-reboot.patch                                           \
"

SRCREV = "090b16927f028ce73a2cde2c05df4a78d7294c1a"

S = "${WORKDIR}/git"

BOARD_CONFIG = "bananapi_mango_defconfig"

do_configure_prepend() {
    oe_runmake -C ${S} O=${B} ${BOARD_CONFIG}
}
