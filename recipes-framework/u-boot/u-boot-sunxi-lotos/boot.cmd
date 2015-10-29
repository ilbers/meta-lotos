setenv bootargs console=ttyS0,115200 console=tty0 sunxi_fb_mem_reserve=16 disp.screen0_output_mode=1280x720p50 hdmi.audio=EDID:0 console=tty1 root=/dev/mmcblk0p2 rootfstype=ext4 elevator=deadline rootwait mem=512M
fatload mmc 0:1 0x71000000 zImage
fatload mmc 0:1 0x71600000 script.bin
fatload mmc 0:1 0x73000000 config.bin
fatload mmc 0:1 0x40000000 mango.uImage
fatload mmc 0:1 0x72000000 freertos.Image
bootm 0x40000000
