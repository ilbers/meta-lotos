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
require recipes-kernel/linux/linux-dtb.inc

PV = "3.18.0"
PR = "rc7"

BOOT_ARGS := "console=ttySAC2 mem=512M root=\/dev\/nfs ip=192.168.178.201:192.168.178.37::255.255.255.0 nfsroot=\/home\/boot\/arndale\/linaro\/nfs rw"

ALLOW_EMPTY_kernel-devicetree = "1"

# Upstream kernel
SRC_URI += "\
    git://git.linaro.org/kernel/linux-linaro-tracking.git;branch=ll_20141204.0;protocol=git \
"

# LotOS framework patches
SRC_URI += "\
    file://0001-exynos5250-remove-second-CPU-from-device-tree.patch \
    file://0002-mango-add-exynos-default-config.patch               \
"

SRCREV = "86dbaade31f718ba8c4f63927ed57896b81971a1"

S = "${WORKDIR}/git"

BOARD_CONFIG = "arndale_mango_defconfig"

do_configure_prepend() {
    # Update boot arguments
    sed -i -e 's/CONFIG_CMDLINE=.*/CONFIG_CMDLINE="${BOOT_ARGS}"/g' ${S}/arch/arm/configs/${BOARD_CONFIG}
    oe_runmake -C ${S} O=${B} ${BOARD_CONFIG}
}

do_install_append() {
        install -d ${D}/boot
        make dtbs || true
        install -m 0644 ${S}/arch/arm/boot/dts/*.dtb ${D}/boot || true
}
