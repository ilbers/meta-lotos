meta-lotos
==========

Copyright (c) 2015-2016 ilbers GmbH

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

Build Targets
=============

The following build targets are available:

 * core-image-minimal

   Console-based Linux image with minimal set of tools installed.

 * core-image-base

   Console-based Linux image with typical set of tools installed.

 * lotos-image-release

   Graphical Linux image with Qt support.

 * lotos-image-devel

   The same as above, but with various devel/testing tools

In addition, all the images include Mango hypervisor extensions.

Supported Boards
================

This layer includes ports for the following boards:

 * Banana Pi (A20)

For more information about supported platforms please contact ilbers GmbH.

Project Documentation
=====================

For more information about LotOS framework features please refer to the getting
started guide:

        doc/getting_started.pdf

Banana Pi Getting Started
=========================

LotOS framework uses *repo* tool simplify project download. If you don't have
this tool on your host, please follow this [steps](https://source.android.com/source/downloading.html)
to get it.

To build LotOS framework for Banana Pi, the following steps has to be done:

1. Checkout sources:

        $ repo init -u https://github.com/ilbers/lotos-manifest.git
        $ repo sync

2. Setup build configuration:

        $ source oe-init-build-env build-folder

    Where *build-folder* is any folder where you want to start build.

3. Edit *conf/local.conf* in your build folder, line 37:

        MACHINE ??= "bpi-lotos"

4. Edit *conf/bblayers.conf* in your build folder and add overlays, line 8:

        BBLAYERS ?= " \
            /opt/dev/yocto/poky/meta \
            /opt/dev/yocto/poky/meta-yocto \
            /opt/dev/yocto/poky/meta-yocto-bsp \

    Add lines:

        /opt/dev/yocto/poky/meta-sunxi \
        /opt/dev/yocto/poky/meta-lotos \

    NOTE: replace */opt/dev/yocto* by the path to your poky tree.

5. Start build

      $ bitbake lotos-image-release

    The ready SD card image can be found in tmp/deploy/images/bpi-lotos/*.bpi-sdimg
