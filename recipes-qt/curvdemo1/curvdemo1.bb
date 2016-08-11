# QWT demo application.
# Derived from: http://qwt.sourceforge.net/
#
# This package is a part of LotOS framework.
#
# Copyright (c) 2016 ilbers GmbH

DESCRIPTION = "Demo application to test QWT for LotOS"

LICENSE = "QWTv1.0"
LIC_FILES_CHKSUM = "file://QWTv1.0;md5=dac2743472b0462ff3cfb4af42051c88"

PV = "1.0"

SRC_URI = "git://github.com/alexbluesman/qwt-example.git"
SRCREV = "9bcf5baa3b43dd657377e2c2faf4424f57ea8114"

DEPENDS = "qwt"

inherit qmake5

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${datadir}/${P}
    install -m 0755 ${B}/curvdemo1 ${D}${datadir}/${P}
}

FILES_${PN}-dbg += "${datadir}/${P}/.debug"
FILES_${PN} += "${datadir}"
