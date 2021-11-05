package dev.villanueva.userland_utility.products.config

import com.fasterxml.jackson.annotation.JsonProperty

class MappingConfiguration {
    @JsonProperty("stylus_buttons")
    var stylusButtons: HashMap<String,
            HashMap<String,
                    LinkedHashSet<Int>
                    >
            > = HashMap()

    var buttons: HashMap<String,
            HashMap<String,
                    LinkedHashSet<Int>
                    >
            > = HashMap()

    var dials: HashMap<String,
            HashMap<String,
                    HashMap<String,
                            LinkedHashSet<Int>
                            >
                    >
            > = HashMap()
}