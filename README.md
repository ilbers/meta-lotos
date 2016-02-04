meta-lotos
==========

Copyright (c) 2015 ilbers GmbH

LotOS framework layer for Yocto project

LotOS engaged the most popular open source operating systems on a single chip using
Mango hypervisor. Based on Yocto, this framework provides a way to build complete
image with Linux and FreeRTOS running in Mango virtual machines.

This framework provides better architecture intended to solve many problems in less
expensive way. Typical applications of LotOS can be:
 * Linux and real-time
 * Fast user response after boot
 * Software with different safety requirements

Maintainers:
    Alexander Smirnov <asmirnov@ilbers.de>

Contacts:
    Mail: lotos@ilbers.de
    Skype: ilbers-suport

Environment
===========

The LotOS framework build was tested on the following Linux distributions:
 * Fedora 20 (64-bit)
 * Fedora 20 (32-bit)
 * Debian 7  (32-bit)

The following build targets were tested:
 * core-image-sato
 * core-image-base

NOTE: for some boards the official vendor layer has to be downloaded.

Supported boards
================

This layer includes ports for the following boards:
 * Banana Pi (A20)
 * Arndale (Exynos5250)

This layer is actively developed and new boards will be added soon. For additional information
about supported platforms please contact ilbers GmbH.

Banana Pi Getting Started
=========================
To build LotOS framework for Banana Pi, the following steps has to be done:

1. Get Yocto project:

        $ git clone git://git.yoctoproject.org/poky.git

2. Checkout supported poky revision:

        $ cd poky
        $ git checkout 221d864042658daa054281aab439b44d54fe94d7

3. Get meta-sunxi layer:

        $ git clone https://github.com/linux-sunxi/meta-sunxi.git

4. Copy 'meta-lotos' to 'poky' folder

5. Setup build configuration:

        $ source oe-init-build-env build-folder

    Where 'build-folder' is any folder where you want to start build.

6. Edit 'conf/local.conf' in your build folder, line 37:

        MACHINE ??= "bananapi-lotos"

7. Edit 'conf/bblayers.conf' in your build folder and add overlays, line 8:

        BBLAYERS ?= " \
            /opt/dev/yocto/poky/meta \
            /opt/dev/yocto/poky/meta-yocto \
            /opt/dev/yocto/poky/meta-yocto-bsp \

    Add lines:

        /opt/dev/yocto/poky/meta-sunxi \
        /opt/dev/yocto/poky/meta-lotos \

    NOTE: replace '/opt/dev/yocto' by the path to your poky tree.

8. Start build

      $ bitbake core-image-sato

    The ready SD card image can be found in tmp/deploy/images/bananapi-lotos/*.sunxi-sdimg

Arnadle Getting Started
=======================

To build LotOS framework for Arndale, the following steps has to be done:

1. Get Yocto project:

        $ git clone git://git.yoctoproject.org/poky.git

2. Checkout supported poky revision:

        $ cd poky
        $ git checkout 221d864042658daa054281aab439b44d54fe94d7

3. Get meta-exynos layer:

        $ git clone https://github.com/slimlogic/meta-exynos.git

4. Copy 'meta-lotos' to 'poky' folder

5. Setup build configuration:

        $ source oe-init-build-env build-folder

    Where 'build-folder' is any folder where you want to start build.

6. Edit 'conf/local.conf' in your build folder, line 37:

        MACHINE ??= "arndale-lotos"

7. Edit 'conf/bblayers.conf' in your build folder and add overlays, line 8:

        BBLAYERS ?= " \
            /opt/dev/yocto/poky/meta \
            /opt/dev/yocto/poky/meta-yocto \
            /opt/dev/yocto/poky/meta-yocto-bsp \

    Add lines:

        /opt/dev/yocto/poky/meta-exynos \
        /opt/dev/yocto/poky/meta-lotos \

    NOTE: replace '/opt/dev/yocto' by the path to your poky tree.

8. Start build

        $ bitbake core-image-base

    After the build, you may find binaries in tmp/deploy/images/arndale-lotos folder.
