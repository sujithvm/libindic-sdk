#
# install android ndk
#
if [ `uname -m` = x86_64 ]; then wget http://dl.google.com/android/ndk/android-ndk-r9d-linux-x86_64.tar.bz2 -O ndk.tgz; else wget http://dl.google.com/android/ndk/android-ndk-r9d-linux-x86.tar.bz2 -O ndk.tgz; fi
tar -xf ndk.tgz
export ANDROID_NDK_HOME=`pwd`/android-ndk-r9d
echo "ndkdir=$ANDROID_NDK_HOME" > gradle.properties
