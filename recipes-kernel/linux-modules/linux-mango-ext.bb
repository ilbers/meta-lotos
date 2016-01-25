# Mango extensions for Linux kernel.
#
# This package is a part of LotOS framework.
#
# Copyright (c) 2015 ilbers GmbH

DESCRIPTION = "Mango extensions for Linux kernel"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV = "v1.0"

inherit module

SRC_URI = "git://github.com/ilbers/linux-modules.git;protocol=git;branch=master"

S = "${WORKDIR}/git"
