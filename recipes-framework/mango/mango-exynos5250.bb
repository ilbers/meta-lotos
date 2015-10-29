# Mango hypervisor binary for Samsung exynos5250.
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
SRC_URI[md5sum] = "21502d801e3fb3bfeed421201de9abec"
SRC_URI[sha256sum] = "634da6b1c9fadab6f390055fbfbcf02c9dd8e70e258503de83ea182e8b92bf4e"

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
