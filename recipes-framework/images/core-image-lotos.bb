include recipes-sato/images/core-image-sato.bb

DEPENDS += "\
    qtbase  \
    qttools \
    "

IMAGE_INSTALL += "\
    qtbase        \
    qtbase-fonts  \
    qttools       \
    "
