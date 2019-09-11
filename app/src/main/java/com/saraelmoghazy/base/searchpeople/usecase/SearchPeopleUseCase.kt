package com.saraelmoghazy.base.searchpeople.usecase

import com.saraelmoghazy.base.baselayer.BaseUseCase
import com.saraelmoghazy.base.data.StarWarsRepository
import com.saraelmoghazy.base.searchpeople.model.PeopleResponse
import retrofit2.Response

/**
 * Created by Sara Elmoghazy.
 */
class SearchPeopleUseCase(id: Int, private val repository: StarWarsRepository) :
    BaseUseCase<PeopleResponse>(id) {

    private var query: String? = null

    fun setParam(query: String?) {
        this.query = query
    }

    override suspend fun buildUseCase(): Response<PeopleResponse> {
        return repository.searchPeople(query)
    }
}