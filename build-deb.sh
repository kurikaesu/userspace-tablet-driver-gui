#!/bin/sh

./gradlew shadowJar
mkdir -p "deb-build/userspace-tablet-driver-utility"
cp -r DEBIAN "deb-build/userspace-tablet-driver-utility"
X
mkdir -p "deb-build/userspace-tablet-driver-utility/usr/local/lib/userspace-tablet-driver-utility"
cp build/libs/*.jar "deb-build/userspace-tablet-driver-utility/usr/local/lib/userspace-tablet-driver-utility"
cp -r config/* "deb-build/userspace-tablet-driver-utility/"

cd deb-build
dpkg-deb --build userspace-tablet-driver-utility
mv userspace-tablet-driver-utility.deb ..

cd ..
rm -rf deb-build
