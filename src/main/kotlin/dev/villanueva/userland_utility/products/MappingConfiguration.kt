package dev.villanueva.userland_utility.products

class MappingConfiguration {
    var buttons: HashMap<String,
            HashMap<String,
                    HashSet<Int>
                    >
            > = HashMap()

    var dials: HashMap<String,
            HashMap<String,
                    HashMap<String,
                            HashSet<Int>
                            >
                    >
            > = HashMap()
}