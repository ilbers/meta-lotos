# Mango demo applications for Linux.
#
# This package is a part of LotOS framework.
#
# Copyright (c) 2015 ilbers GmbH

DESCRIPTION = "Mango demo applications for Linux"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV = "v1.0"

SRC_URI = "\
    git://github.com/ilbers/mango-apps.git;protocol=git;branch=master \
    file://mango                                                      \
"
SRCREV = "4fd527585b0c05fe154c00defcd24f8368447797"

S = "${WORKDIR}/git"

inherit autotools deploy

pkg_postinst_${PN} () {
    #
    # Create directories:
    #   $D${sysconfdir}/init.d - will hold the scripts
    #   $D${sysconfdir}/rcS.d  - will contain a link to the script that runs at startup
    #   $D${sysconfdir}/rc5.d  - will contain a link to the script that runs at runlevel=5
    #
    install -d $D${sysconfdir}/init.d
    install -d $D${sysconfdir}/rc1.d
    install -d $D${sysconfdir}/rc2.d
    install -d $D${sysconfdir}/rc3.d
    install -d $D${sysconfdir}/rc4.d
    install -d $D${sysconfdir}/rc5.d

    #
    # Install file in to the image
    #
    install -m 0755 ${WORKDIR}/mango  $D${sysconfdir}/init.d/

    #
    # Create symbolic links from the runlevel directories to the script files.
    # Links of the form S... and K... mean the script when be called when
    # entering / exiting the runlevel designated by the containing directory.
    #
    ln -sf ../init.d/mango $D${sysconfdir}/rc1.d/K90mango
    ln -sf ../init.d/mango $D${sysconfdir}/rc2.d/K90mango
    ln -sf ../init.d/mango $D${sysconfdir}/rc3.d/K90mango
    ln -sf ../init.d/mango $D${sysconfdir}/rc4.d/K90mango
    ln -sf ../init.d/mango $D${sysconfdir}/rc5.d/S90mango
}
