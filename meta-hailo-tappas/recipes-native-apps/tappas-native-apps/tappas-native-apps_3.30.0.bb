DESCRIPTION = "TAPPAS Hailo15 native applications recipe, \
               the recipe copies the app script, hef files and media to /home/root/apps \
               the apps hefs and media urls are taken from files/download_reqs.txt"

inherit tappas-base tappas-apps-base

S = "${WORKDIR}/git/apps/h15/native"
LIC_FILES_CHKSUM = "file://../../../../LICENSE;md5=6f55b1feb4854e8ac5dd4a1bd4a378ed"
DEPENDS += " opencv"

REQS_PATH = "${FILE_DIRNAME}/files/"
REQS_HAILO15_FILE = "${REQS_PATH}download_reqs_hailo15.txt"

python set_reqs_file() {
    if 'hailo15' in d.getVar('MACHINE'):
        d.setVar('REQS_FILE', d.getVar('REQS_HAILO15_FILE'))
        d.setVar('ARM_APPS_DIR', d.getVar('HAILO15_DIR'))
    else:
        d.setVar('REQS_FILE', d.getVar('REQS_HAILO15_FILE'))
        d.setVar('ARM_APPS_DIR', d.getVar('HAILO15_DIR'))
}

FILES:${PN} += " /home/root/apps/*"
FILES:${PN}-lib += ""
RDEPENDS:${PN}-staticdev = ""
RDEPENDS:${PN}-dev = ""
RDEPENDS:${PN}-dbg = ""
