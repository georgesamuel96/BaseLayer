package com.saraelmoghazy.base.usecase

import com.saraelmoghazy.base.baselayer.BaseUseCase
import com.saraelmoghazy.base.data.StarWarsRepository
import com.saraelmoghazy.base.model.PeopleResponse
import retrofit2.Response

/**
 * Created by Sara Elmoghazy.
 */
class StarWarUseCase(id: Int, private val repository: StarWarsRepository) : BaseUseCase<PeopleResponse>(id) {

    override suspend fun buildUseCase(): Response<PeopleResponse> {
        return repository.searchPeople("")
    }
}