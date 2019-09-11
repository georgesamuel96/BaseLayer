package com.saraelmoghazy.base.searchpeople.model

import java.io.Serializable

data class ResultsItem(
    val films: ArrayList<String?>? = null,
    val homeworld: String? = null,
    val birthYear: String? = null,
    val species: List<String?>? = null,
    val name: String? = null,
    val height: String? = null
) : Serializable
