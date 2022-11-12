package me.brisson.ecomlabs.data.model

data class CEP(val value: String) {

    init { validateValue() }

    private fun validateValue() {
        val regex = Regex("[0-9]{5}-[0-9]{3}")
        assert(regex.matches(value)) { "CEP has wrong format" } // Throws an AssertionError if the value is false
    }
}
