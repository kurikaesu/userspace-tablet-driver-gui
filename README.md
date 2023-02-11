# Kuri's Userspace tablet driver utility (GUI)

This is a new GUI implementation for the userland driver I've written here: https://github.com/kurikaesu/userspace-tablet-driver-daemon

### Supports
- XP-Pen Deco Pro Small
- XP-Pen Deco Pro Medium
- XP-Pen Deco 01 v2
- XP-Pen Deco 02
- XP-Pen Deco 03
- XP-Pen Deco mini7/mini7w
- XP-Pen Artist 12 Pro
- XP-Pen Artist 12 Pro (2nd Gen)
- XP-Pen Artist 12 (2nd Gen)
- XP-Pen Artist 13.3 Pro
- XP-Pen Artist 16 Pro
- XP-Pen Artist Pro 16
- XP-Pen Artist 22r Pro
- XP-Pen Artist 24 Pro
- XP-Pen Innovator 16
- XP-Pen Star G430S
- XP-Pen Star G640
- XP-Pen AC19 Shortcut Remote
- Huion Kamvas Pro 13
- Huion WH1409 v2
- Huion WH1409 (2048)
- Huion H1161
- Huion KD100 mini Keydial
- Gaomon M10K Pro
- Gaomon M10K 2018

## Building and running
### Prerequisites:

- OpenJDK 11+

The above should be available from your package managers.
Once installed:
```
git clone https://github.com/kurikaesu/userspace-tablet-driver-gui.git
cd userspace-tablet-driver-gui
./gradlew build
./gradlew run
```

The userspace driver daemon will need to be running as this GUI will communicate directly with it.