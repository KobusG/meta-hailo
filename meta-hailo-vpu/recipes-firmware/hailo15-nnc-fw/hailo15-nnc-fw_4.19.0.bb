DESCRIPTION = "Hailo15_NNC FW. \
               This recipe copy the already compiled Hailo15_NNC firmware to the image"

BASE_URI = "https://hailo-hailort.s3.eu-west-2.amazonaws.com"
FW_AWS_DIR = "Hailo15/Hailort/${PV}/FW"
FW = "hailo15_nnc_fw.${PV}.bin"
LICENSE_FILE = "LICENSE"
SRC_URI = "${BASE_URI}/${FW_AWS_DIR}/${FW};md5sum=f04c2db40042a3d53a6185d65e8ce1b4 \
		   ${BASE_URI}/${FW_AWS_DIR}/${LICENSE_FILE};md5sum=263ee034adc02556d59ab1ebdaea2cda"

LICENSE = "LICENSE"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${LICENSE_FILE};md5=263ee034adc02556d59ab1ebdaea2cda"

FW_PATH = "${WORKDIR}/${FW}"

do_install() {
	# Stores hailo15_nnc_fw.bin in the rootfs under /lib/firmware/hailo
	install -d ${D}${nonarch_base_libdir}/firmware/hailo
	install -m 0755 ${FW_PATH} ${D}${nonarch_base_libdir}/firmware/hailo/hailo15_nnc_fw.bin
}

FILES:${PN} += "${nonarch_base_libdir} ${nonarch_base_libdir}/* ${nonarch_base_libdir}/firmware/hailo/hailo15_nnc_fw*"
