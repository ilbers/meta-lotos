# QWT port for LotOS
# Based on code by Mikhail Kuchuk, https://github.com/mikuka
#
# This software is a part of LotOS framework.
#
# Copyright (c) 2016, ilbers GmbH

DESCRIPTION = "Qt Widget Extension for Technical Applications"
SECTION = "libs"
PR = "r1"

# LGPLv2.1 + some exceptions
LICENSE = "QWTv1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=dac2743472b0462ff3cfb4af42051c88"

SRC_URI = "${SOURCEFORGE_MIRROR}/qwt/qwt-${PV}.tar.bz2"

SRC_URI[md5sum] = "9c88db1774fa7e3045af063bbde44d7d"
SRC_URI[sha256sum] = "2b08f18d1d3970e7c3c6096d850f17aea6b54459389731d3ce715d193e243d0c"

inherit qmake5 qmake5_paths

DEPENDS = "qtbase"

PACKAGES += "${PN}-mkspecs"
FILES_${PN}-mkspecs = "\
    ${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs \
"

S = "${WORKDIR}/qwt-${PV}"

QWT_OPENGL = "QWT_CONFIG     += QwtOpenGL"
QWT_EXAMPLES = "QWT_CONFIG     += QwtExamples"
QWT_DESIGNER = "QWT_CONFIG     += QwtDesigner"

python __anonymous() {
    packageconfig = d.getVar("PACKAGECONFIG", True)
    if packageconfig.find("opengl") == -1:
        d.prependVar("QWT_OPENGL", "#")
    if packageconfig.find("qwt-examples") == -1:
        d.prependVar("QWT_EXAMPLES", "#")
    if packageconfig.find("qwt-plugins") == -1:
        d.prependVar("QWT_DESIGNER", "#")
}

do_configure_prepend () {
    sed -i -e 's:QT += designer:LIBS += ${OE_QMAKE_PATH_LIBS}/qtcreator/plugins -lQt5Designer:' ${S}/designer/designer.pro
    sed -i -e 's:/usr/local/qwt-$$QWT_VERSION:${D}${prefix}:g' ${S}/qwtconfig.pri
    sed -i -e 's:QWT_CONFIG     += QwtDesigner:${QWT_DESIGNER}:' ${S}/qwtconfig.pri
    sed -i -e 's:QWT_CONFIG     += QwtOpenGL:${QWT_OPENGL}:' ${S}/qwtconfig.pri
    sed -i -e 's:QWT_CONFIG     += QwtExamples:${QWT_EXAMPLES}:' ${S}/qwtconfig.pri
    sed -i -e 's:QWT_CONFIG     += QwtSvg:#QWT_CONFIG     += QwtSvg:' ${S}/qwtconfig.pri
}

do_install () {
    oe_runmake -e install
    install -d ${D}${datadir}/doc/${PN}
    mv ${D}${prefix}/doc/* ${D}${datadir}/doc/${PN}/
    rmdir ${D}${prefix}/doc
    if [ -e ${S}/../build/examples/bin ] ; then
        cd ${S}/examples
        install -d ${D}/${bindir}
        cd bin/
        for i in * ; do
            cp -pPR ${i} ${D}/${bindir}/${i}
        done
    fi
    if [ -e ${S}/../build/build/designer ] ; then
        mv ${D}${prefix}/plugins ${D}${libdir}${QT_DIR_NAME}
    fi
    install -d ${D}/usr/${feature}
    install -d ${D}${libdir}${QT_DIR_NAME}
}

INSANE_SKIP_${PN} = "installed-vs-shipped" 
PACKAGES_prepend = "${PN}-examples ${PN}-features ${PN}-plugins "

FILES_${PN}-examples = "${bindir}/*"
FILES_${PN}-features = "${prefix}/features"
FILES_${PN}-plugins = "${libdir}${QT_DIR_NAME}/plugins/designer/*.so"
FILES_${PN}-dbg += "${libdir}${QT_DIR_NAME}/plugins/designer/.debug"
