package com.saraelmoghazy.base.chardetails

import androidx.lifecycle.MutableLiveData
import com.saraelmoghazy.base.R
import com.saraelmoghazy.base.baselayer.BaseViewModel
import com.saraelmoghazy.base.baselayer.UseCase
import com.saraelmoghazy.base.chardetails.model.CharacterUiModel
import com.saraelmoghazy.base.chardetails.model.PlanetsResponse
import com.saraelmoghazy.base.chardetails.model.SpeciesResponse
import com.saraelmoghazy.base.chardetails.usecase.PlanetsUseCase
import com.saraelmoghazy.base.chardetails.usecase.SpeciesUseCase
import com.saraelmoghazy.base.searchpeople.model.ResultsItem

class CharacterDetailsViewModel(speciesUseCase: SpeciesUseCase, planetsUseCase: PlanetsUseCase) :
    BaseViewModel() {


    @field:UseCase(R.id.SpeciesUseCase)
    val speciesUseCase: SpeciesUseCase = speciesUseCase
    @field:UseCase(R.id.PlanetsUseCase)
    val planetsUseCase: PlanetsUseCase = planetsUseCase
    private var speciesId: Int = 0
    private var planetId: Int = 0
    private var filmIds = ArrayList<Int>()
    private val characterUiModel = CharacterUiModel()
    val character = MutableLiveData<CharacterUiModel>()

    init {
        start()
    }

    fun getCharacter(item: ResultsItem) {
        characterUiModel.height = item.height
        characterUiModel.birthYear = item.birthYear
        characterUiModel.name = item.name
        speciesId = item.species?.get(0)?.replace("[^0-9]", "")?.toInt()!!
        planetId = item.homeworld?.replace("[^0-9]", "")?.toInt()!!
        for (film in item.films!!) {
            filmIds.add(film?.replace("[^0-9]", "")?.toInt()!!)
        }
    }


    fun getSpecies(id: Int) {
        speciesUseCase.speciesId = id
        loadingIndicatorLiveData.value = true
        executeUseCase(speciesUseCase)
    }

    override fun <M> onSuccess(response: M?) {
        when (response) {
            is SpeciesResponse -> {
                executeUseCase(planetsUseCase)
            }
            is PlanetsResponse -> {
                executeUseCase(planetsUseCase)
            }
            if()
        }
    }

