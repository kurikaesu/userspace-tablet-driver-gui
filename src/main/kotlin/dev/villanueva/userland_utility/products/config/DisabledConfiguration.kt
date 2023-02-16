package dev.villanueva.userland_utility.products.config

import com.fasterxml.jackson.annotation.JsonProperty

class DisabledConfiguration {
    @JsonProperty("stylus_buttons")
    var stylusButtons: HashSet<String> = HashSet()

    var buttons: HashSet<String> = HashSet()

    var dials: HashSet<String> = HashSet()
}