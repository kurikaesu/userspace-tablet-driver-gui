#!/bin/sh

./gradlew shadowJar
mkdir -p "deb-build/xp-pen-userland-utility"
cp -r DEBIAN "deb-build/xp-pen-userland-utility"

mkdir -p "deb-build/xp-pen-userland-utility/usr/local/lib/xp-pen-userland-utility"
cp build/libs/*.jar "deb-build/xp-pen-userland-utility/usr/local/lib/xp-pen-userland-utility"
cp -r config/* "deb-build/xp-pen-userland-utility/"

cd deb-build
dpkg-deb --build xp-pen-userland-utility
mv xp-pen-userland-utility.deb ..

cd ..
rm -rf deb-build
