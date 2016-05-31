# Utils for Mango hypervisor configuration.
#
# This package is a part of LotOS framework.
#
# Copyright (c) 2015-2016 ilbers GmbH

DESCRIPTION = "Utils for Mango hypervisor configuration"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV = "v1.0"

SRC_URI = "git://github.com/ilbers/mango-utils.git;protocol=git;branch=master"

S = "${WORKDIR}/git"

BBCLASSEXTEND = "native nativesdk"
SECTION = "devel"

inherit autotools deploy native

do_deploy() {
	${B}/mng2bin/mng2bin ${S}/mng2bin/configs/${BOARD_TYPE}_2parts.mng ${S}/config.bin
	install -m 0644 ${S}/config.bin ${DEPLOYDIR}
}

addtask deploy before do_build after do_compile

do_install[noexec] = "1"
do_package[noexec] = "1"
do_packagedata[noexec] = "1"
do_package_write[noexec] = "1"
do_package_write_ipk[noexec] = "1"
do_package_write_rpm[noexec] = "1"
do_package_write_deb[noexec] = "1"
do_populate_sysroot[noexec] = "1"
