DESCRIPTION = "pyhailort - hailo's python API \
               the recipe installed using pyhailort setuptools into python/site-packages \
               the recipe contains all the python dependencies and it's currently supported by python 3.6 and 3.7 (TODO: update python versions)" 

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://../../../../LICENSE;md5=48b1c947c88868c23e4fb874890be6fc \
                    file://../../../../LICENSE-3RD-PARTY.md;md5=0b218cdfba3046481fb21963f50be29a"

SRC_URI = "git://git@github.com/hailo-ai/hailort.git;protocol=https;branch=master"
SRCREV = "01e4c7f5a7463cc61ef1b2d22c31dd80a3a07d95"

S = "${WORKDIR}/git/hailort/libhailort/bindings/python/platform"


inherit pkgconfig hailort-base python3native setuptools3

DEPENDS += "python3-wheel-native libhailort python3-pybind11 git-native"
RDEPENDS_${PN} += "libhailort python3-future python3-importlib-metadata python3-netifaces \
                   python3-appdirs python3-contextlib2 python3-netaddr \
                   python3-argcomplete python3-verboselogs python3-numpy python3-setuptools"

do_compile_prepend() {
    # these cmake params should have been propagated directly to cmake. However, we inherit setuptools3 and setup.py
    # is responsible for cmake execution. These params must pass to setup.py the ENV variable, and from there, pass to
    # cmake.

    # allow linkage against HailoRT
    export HailoRT_DIR=${STAGING_LIBDIR}/cmake/HailoRT
    # allow linkage against pybind11
    export PYTHON_INCLUDE_DIRS=${STAGING_INCDIR}/python${PYTHON_BASEVERSION}
    # define the toolchain file
    export CMAKE_TOOLCHAIN_FILE=${WORKDIR}/toolchain.cmake
}

# prevents the following error:
# pyhailort-... do_package: QA Issue: File '..._pyhailort...so' from pyhailort was already stripped, this will prevent future debugging! [already-stripped]
INSANE_SKIP_${PN} += "already-stripped"
