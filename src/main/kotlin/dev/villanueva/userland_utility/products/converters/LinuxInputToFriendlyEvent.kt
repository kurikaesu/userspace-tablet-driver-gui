package dev.villanueva.userland_utility.products.converters

import java.io.File
import java.util.concurrent.TimeUnit

class LinuxInputToFriendlyEvent {
    companion object {
        private val evdevKeyMap: HashMap<Int, String> = HashMap()
        private val reverseEvdevKeyMap: HashMap<String, Int> = HashMap()
        private val evdevToKey: HashMap<String, String> = HashMap()

        init {
            val evdevKeycodeFilePath = "/usr/share/X11/xkb/keycodes/evdev"
            val evdevKeycodeFile = File(evdevKeycodeFilePath)
            val evdevLines = evdevKeycodeFile.inputStream().reader().readLines()

            val configLineRegex = Regex("([<>\\w]+) ?= ?([0-9]+);")
            val aliasedConfigLineRegex = Regex("alias ([<>\\w]+) ?= ?([<>\\w]+);")

            // Go through each line looking for assignments
            for (line in evdevLines) {
                if (!line.trim().startsWith("//")) {
                    val matchResult = configLineRegex.find(line)
                    if (matchResult != null) {
                        val (keySym, code) = matchResult.destructured
                        evdevKeyMap[code.toInt()] = keySym
                        reverseEvdevKeyMap[keySym] = code.toInt()
                    }

                    val aliasedResult = aliasedConfigLineRegex.find(line)
                    if (aliasedResult != null) {
                        val (keySym, alias) = aliasedResult.destructured
                        if (reverseEvdevKeyMap.containsKey(alias)) {
                            val evdevKey = reverseEvdevKeyMap[alias]
                            evdevKeyMap[evdevKey!!] = keySym
                            reverseEvdevKeyMap[keySym] = evdevKey
                        }
                    }
                }
            }

            // Get the current keyboard layout
            val layoutString = "setxkbmap -query".runCommand()
            var layout = "us"
            if (layoutString != null) {
                val layoutLineRegex = Regex("layout:\\W+[\\w,]+")
                val matchResult = layoutLineRegex.find(layoutString)
                if (matchResult != null) {
                    val parts = matchResult.value.replace(" ", "").split(":")
                    layout = parts[1].split(",")[0]
                }
            }

            // Take the symbol config
            val symbolKeycodeFilePath = "/usr/share/X11/xkb/symbols/$layout"
            val symbolKeycodeFile = File(symbolKeycodeFilePath)
            val symbolKeycodeLines = symbolKeycodeFile.inputStream().reader().readLines()

            val symbolLineRegex = Regex("key ([<>\\w]+) ?\\{([\\[ \\w,\\t\\]]+)};")
            var foundSection = false
            for (line in symbolKeycodeLines) {
                // Search for the symbol section first
                if (!foundSection) {
                    if (line.startsWith("xkb_symbols") && line.contains("common")) {
                        foundSection = true
                    } else {
                        continue
                    }
                }

                // Check to see if we have hit a different section. If so, bail out
                if ((line.startsWith("xkb_symbols") && !line.contains("common")) || line.startsWith("partial")) {
                    break
                }

                val matchResult = symbolLineRegex.find(line)
                if (matchResult != null) {
                    val (keySym, binding) = matchResult.destructured
                    val bindingParts = binding.replace("[", "").replace("]", "").trim().split(",")
                    evdevToKey[keySym] = evdevTranslateModifierKeyName(bindingParts[0])
                }
            }
        }

        private fun evdevTranslateModifierKeyName(keyName: String): String {
            return when (keyName) {
                "Control_L" -> "Left Control"
                "at" -> "@"
                "backslash" -> "\\"
                "slash" -> "/"
                "colon" -> ":"
                "semicolon" -> ";"
                "period" -> "."
                "comma" -> ","
                "bracketleft" -> "["
                "bracketright" -> "]"
                else -> keyName.uppercase()
            }
        }

        private fun String.runCommand(
            workingDir: File = File("."),
            timeoutAmount: Long = 60,
            timeoutUnit: TimeUnit = TimeUnit.SECONDS
        ): String? = kotlin.runCatching {
            ProcessBuilder("\\s".toRegex().split(this))
                .directory(workingDir)
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.PIPE)
                .start().also { it.waitFor(timeoutAmount, timeoutUnit)}
                .inputStream.bufferedReader().readText()
        }.onFailure { it.printStackTrace() }.getOrNull()

        private val eventKeyMapping: HashMap<Int, String> = hashMapOf(
            1 to "Escape",
            2 to "1",
            3 to "2",
            4 to "3",
            5 to "4",
            6 to "5",
            7 to "6",
            8 to "7",
            9 to "8",
            10 to "9",
            11 to "0",
            12 to "Minus",
            13 to "Equals",
            14 to "Backspace",
            15 to "Tab",
            16 to "Q",
            17 to "W",
            18 to "E",
            19 to "R",
            20 to "T",
            21 to "Y",
            22 to "U",
            23 to "I",
            24 to "O",
            25 to "P",
            26 to "[",
            27 to "]",
            28 to "Enter",
            29 to "Control",
            30 to "A",
            31 to "S",
            32 to "D",
            33 to "F",
            34 to "G",
            35 to "H",
            36 to "J",
            37 to "K",
            38 to "L",
            39 to "Semicolon",
            40 to "Apostraphe",
            41 to "Grave",
            42 to "Shift",
            43 to "Back Slash",
            44 to "Z",
            45 to "X",
            46 to "C",
            47 to "V",
            48 to "B",
            49 to "N",
            50 to "M",
            51 to "Comma",
            52 to "Period",
            53 to "/",
            54 to "Right Shift",
            55 to "Keypad Asterisk",
            56 to "Alt",
            57 to "Space",
            58 to "Caps Lock",
            59 to "F1",
            60 to "F2",
            61 to "F3",
            62 to "F4",
            63 to "F5",
            64 to "F6",
            65 to "F7",
            66 to "F8",
            67 to "F9",
            68 to "F10",
            69 to "Num Lock",
            70 to "Scroll Lock",
            71 to "Keypad 7",
            72 to "Keypad 8",
            73 to "Keypad 9",
            74 to "Keypad Minus",
            75 to "Keypad 4",
            76 to "Keypad 5",
            77 to "Keypad 6",
            78 to "Keypad Plus",
            79 to "Keypad 1",
            80 to "Keypad 2",
            81 to "Keypad 3",
            82 to "Keypad 0",
            83 to "Keypad Period",
            87 to "F11",
            88 to "F12",
            89 to "Ro",
            92 to "Henkan",
            93 to "Katakana Hiragana",
            94 to "Muhenkan",
            96 to "Keypad Enter",
            97 to "Right Control",
            98 to "Keypad Slash",
            99 to "Print Screen",
            100 to "Right Alt",
            102 to "Home",
            103 to "Up",
            104 to "Page Up",
            105 to "Left",
            106 to "Right",
            107 to "End",
            108 to "Down",
            109 to "Page Down",
            110 to "Insert",
            111 to "Delete",
            119 to "Pause",
            124 to "¥",
            125 to "Left Meta",
            126 to "Right Meta",
            127 to "Compose",
            139 to "Menu"
        )

        private val shiftKeyMapping: HashMap<Int, Int> = hashMapOf(
            3638 to 54,
            3639 to 103,
            3655 to 89,
            3663 to 107,
            3667 to 111,
            3675 to 108,
            3677 to 109,
            57416 to 103,
            57419 to 92,
            57421 to 94,
            57424 to 96,
        )

        private val rawKeyMapping: HashMap<Int, Int> = hashMapOf(
            92 to 124,
            65299 to 119,
            65319 to 93,
            65360 to 102,
            65361 to 105,
            65363 to 106,
            65365 to 104,
            65367 to 107,
            65377 to 99,
            65379 to 110,
            65383 to 139,
            65407 to 69,
            65429 to 102,
            65430 to 105,
            65431 to 103,
            65432 to 106,
            65433 to 108,
            65434 to 104,
            65435 to 109,
            65436 to 107,
            65437 to 76,
            65438 to 110,
            65439 to 111,
            65450 to 55,
            65451 to 78,
            65453 to 74,
            65454 to 83,
            65455 to 98,
            65456 to 82,
            65457 to 79,
            65458 to 80,
            65459 to 81,
            65460 to 75,
            65461 to 76,
            65462 to 77,
            65463 to 71,
            65464 to 72,
            65465 to 73,
            65508 to 97,
            65514 to 100,
            65516 to 126,
            65535 to 111,
        )

        private val eventMouseButtonMapping: HashMap<Int, String> = hashMapOf(
            0x110 to "Mouse Button Left",
            0x111 to "Mouse Button Right",
            0x112 to "Mouse Button Middle",
            0x113 to "Mouse Button Side",
            0x114 to "Mouse Button Extra",
        )

        private val mouseButtonToEvent: HashMap<Int, Int> = hashMapOf(
            1 to 0x110,
            2 to 0x112,
            3 to 0x111,
            4 to 0x113,
            5 to 0x114,
        )

        fun getKeyDisplayName(value: Int): String? {
            if (evdevKeyMap.containsKey(value + 8)) {
                if (evdevToKey[evdevKeyMap[value + 8]] != null) {
                    return (evdevToKey[evdevKeyMap[value + 8]])
                }
            }
            return eventKeyMapping[value]
        }

        fun translateShiftedKeys(rawCode: Int, keyCode: Int, location: Int): Int? {
            // Very specific for print-screen
            if (location == 1 && keyCode == 3667 && rawCode == 65377) {
                return 99
            }

            if (location < 3 && !rawKeyMapping.containsKey(rawCode) && eventKeyMapping.containsKey(keyCode)) {
                return keyCode
            }

            if (location < 4 && shiftKeyMapping.containsKey(keyCode))
                return shiftKeyMapping[keyCode]

            return rawKeyMapping[rawCode]
        }

        fun getMouseDisplayName(value: Int): String? {
            return eventMouseButtonMapping[value]
        }

        fun translateMouseEvent(value: Int): Int? {
            return mouseButtonToEvent[value]
        }
    }
}