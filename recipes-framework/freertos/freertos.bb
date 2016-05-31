# FreeRTOS is a market leading RTOS from Real Time Engineers Ltd.
#
# This package is a part of LotOS framework.
#
# Copyright (c) 2015-2016 ilbers GmbH

DESCRIPTION = "FreeRTOS v8.2.1 operating system"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://License/license.txt;md5=4f022faad9b16d65e7b1f5203556e230"

PV = "8.2.1"
PR = "v1.0-mango"

SRC_URI = "git://github.com/ilbers/freertos.git;protocol=git;branch=mango"

S = "${WORKDIR}/git"

inherit deploy

do_compile() {
	unset LDFLAGS
	unset CFLAGS
	unset CPPFLAGS

	make CFLAGS_EXTRA="--sysroot ${STAGING_DIR_TARGET}" \
	     PLATFORM=${SOC_TYPE}                           \
	     CROSS_COMPILE="${TARGET_PREFIX}"               \
	     -C ${S}
}

do_deploy() {
	make INSTALL_DIR=${DEPLOYDIR} -C ${S} install
}

addtask deploy before do_build after do_compile

do_configure[noexec] = "1"
do_install[noexec] = "1"
do_package[noexec] = "1"
do_packagedata[noexec] = "1"
do_package_write[noexec] = "1"
do_package_write_ipk[noexec] = "1"
do_package_write_rpm[noexec] = "1"
do_package_write_deb[noexec] = "1"
do_populate_sysroot[noexec] = "1"
