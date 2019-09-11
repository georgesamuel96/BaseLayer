package com.saraelmoghazy.base.searchpeople.model

data class ResultsItem(
	val films: List<String?>? = null,
	val homeworld: String? = null,
	val gender: String? = null,
	val skinColor: String? = null,
	val edited: String? = null,
	val created: String? = null,
	val mass: String? = null,
	val vehicles: List<Any?>? = null,
	val url: String? = null,
	val hairColor: String? = null,
	val birthYear: String? = null,
	val eyeColor: String? = null,
	val species: List<String?>? = null,
	val starships: List<Any?>? = null,
	val name: String? = null,
	val height: String? = null
)
