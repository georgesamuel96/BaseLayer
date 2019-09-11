package com.saraelmoghazy.base.chardetails.usecase

import com.saraelmoghazy.base.baselayer.BaseUseCase
import com.saraelmoghazy.base.chardetails.model.PlanetsResponse
import com.saraelmoghazy.base.data.StarWarsRepository
import retrofit2.Response

class PlanetsUseCase(id: Int, private val repository: StarWarsRepository) :
    BaseUseCase<PlanetsResponse>(id) {

    var planetId: Int = 0

    override suspend fun buildUseCase(): Response<PlanetsResponse> {
      return  repository.getPlanets(planetId)
    }
}