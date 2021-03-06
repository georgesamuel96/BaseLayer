package com.saraelmoghazy.base.searchpeople.model

data class PeopleResponse(
	val next: Any? = null,
	val previous: String? = null,
	val count: Int? = null,
	val results: List<ResultsItem>
)
