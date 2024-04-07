DESCRIPTION = "tappas-tracers GStreamer plugin \
               compiles the tappas libgsttracer gstreamer plugin \ 
               and copies it to usr/lib/gstreamer-1.0 (gstreamer's plugins directory) "
               
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM += "file://../../LICENSE;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "git://git@github.com/hailo-ai/tappas.git;protocol=https;branch=master"
SRCREV = "d6eb4c9543d0cd50869f85c6dab9e48c8b741b6b"

inherit hailotools-base

do_install_append() {
    # Meson installs shared objects in apps target,
    # we remove it from the rootfs to prevent duplication with libgsthailotools
    rm -rf ${D}/usr/lib/libgsthailometa*
    rm -rf ${D}/usr/include/gsthailometa
    rm -rf ${D}/usr/lib/pkgconfig/gsthailometa.pc

    rm -f ${D}/${libdir}/gstreamer-1.0/libgsthailotracers.so
    find ${D}/${libdir}/gstreamer-1.0/ -name 'libgsthailotracers.so.[0-9]' -delete
    mv -f ${D}/${libdir}/gstreamer-1.0/libgsthailotracers.so.${PV} ${D}/${libdir}/gstreamer-1.0/libgsthailotracers.so
}


DEPENDS += "glib-2.0-native glib-2.0 gstreamer1.0 gstreamer1.0-plugins-base libgsthailotools"

TAPPAS_BUILD_TARGET = "tracers"

FILES_${PN} += "/usr/lib/gstreamer-1.0/libgsthailotracers.so /usr/lib/gstreamer-1.0/libgsthailotracers.so.${PV}"
FILES_${PN}-lib += "/usr/lib/gstreamer-1.0/libgsthailotracers.so.${PV} /usr/lib/gstreamer-1.0/libgsthailotracers.so"
RDEPENDS_${PN}-staticdev = ""
RDEPENDS_${PN}-dev = ""
RDEPENDS_${PN}-dbg = ""