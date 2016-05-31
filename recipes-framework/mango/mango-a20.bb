# Mango hypervisor binary for Allwinner A20.
#
# This package is a part of LotOS framework.
#
# Copyright (c) 2015-2016 ilbers GmbH

DESCRIPTION = "Mango hypervisor"

# Mango hypervisor is a property of ilbers GmbH
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://license.txt;md5=11b78b1f12411a8c70462b805049c6bf"

PV = "1.4"

SRC_URI = "http://dl.ilbers.de/dl/mango-${PV}-${SOC_TYPE}.tar.gz"
SRC_URI[md5sum] = "9f36c2c663c11a5e6cc2f52121b02998"
SRC_URI[sha256sum] = "91bc08f5c30461474afe29350538de5ac577ce4f35ba5bad7e0f7ed354d0af17"

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
