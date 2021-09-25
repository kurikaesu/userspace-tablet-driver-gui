# Kuri's XP-Pen Userland driver Util (GUI)

I need a better name for this!

This is a new GUI implementation for the userland driver I've written here: https://github.com/kurikaesu/xp-pen-userland

The code is really, really messy right now. Seriously messy.

**It currently only supports the Deco Pro Small and no other device.**

The python GUI will no longer be updates as there are severe limitations on assigning keys to it due to the library I used (pynput).

## Building and running
### Prerequisites:

- OpenJDK 11+

Both of the above should be available from your package managers.
Once installed:
```
git clone https://github.com/kurikaesu/userspace-tablet-driver-gui.git
cd userspace-tablet-driver-gui
./gradlew build
./gradlew run
```

The userspace driver daemon will need to be running as this GUI will communicate directly with it.