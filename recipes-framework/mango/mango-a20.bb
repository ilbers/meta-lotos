# Mango hypervisor binary for Allwinner A20.
#
# This package is a part of LotOS framework.
#
# Copyright (c) 2015 ilbers GmbH

DESCRIPTION = "Mango hypervisor"

# Mango hypervisor is a property of ilbers GmbH
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://license.txt;md5=11b78b1f12411a8c70462b805049c6bf"

PV = "1.2"

SRC_URI = "http://dl.ilbers.de/dl/mango-${PV}-${SOC_TYPE}.tar.gz"
SRC_URI[md5sum] = "7a2be3fc3373a7dc2f15c71f39e6d3a5"
SRC_URI[sha256sum] = "449bf5d29987a49b58deb997745e152077fd0db2ded51d9734d35704f65315d8"

S = "${WORKDIR}/mango-${PV}-${SOC_TYPE}"

inherit deploy

do_deploy() {
	make INSTALL_PATH=${DEPLOYDIR} -C ${S} install
}

addtask deploy before do_build after do_compile

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"
do_package[noexec] = "1"
do_packagedata[noexec] = "1"
do_package_write[noexec] = "1"
do_package_write_ipk[noexec] = "1"
do_package_write_rpm[noexec] = "1"
do_package_write_deb[noexec] = "1"
do_populate_sysroot[noexec] = "1"
