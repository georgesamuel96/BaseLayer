package com.saraelmoghazy.base.chardetails.viewmodel

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


class CharacterDetailsViewModel(
    @field:UseCase(R.id.SpeciesUseCase) val speciesUseCase: SpeciesUseCase, @field:UseCase(
        R.id.PlanetsUseCase
    ) val planetsUseCase: PlanetsUseCase
) :
    BaseViewModel() {
    private lateinit var character: ResultsItem
    private var characterUiModel: CharacterUiModel? = null
    val characterLiveData = MutableLiveData<CharacterUiModel>()

    init {
        start()
    }

    fun getCharacter(character: ResultsItem) {
        if (this.characterUiModel == null) {
            this.character = character
            characterUiModel = CharacterUiModel()
            characterUiModel?.height = character.height
            characterUiModel?.birthYear = character.birthYear
            characterUiModel?.name = character.name
            for (film in character.films!!) {
                film?.let { characterUiModel?.filmResponse?.add(it) }
            }
            getSpecies()
        }
    }

    private fun getSpecies() {
        speciesUseCase.speciesId = extractId(character.species?.get(0))
        loadingIndicator.isLoading = true
        loadingIndicatorLiveData.value = loadingIndicator
        executeUseCase(speciesUseCase)
    }

    private fun getPlanets() {
        planetsUseCase.planetId = extractId(character.homeworld)
        loadingIndicator.isLoading = true
        loadingIndicatorLiveData.value = loadingIndicator
        executeUseCase(planetsUseCase)
    }

    override fun <M> onSuccess(response: M?) {
        when (response) {
            is SpeciesResponse -> {
                characterUiModel?.speciesLanguage = (response as SpeciesResponse).language
                characterUiModel?.speciesHomeWorld = (response as SpeciesResponse).homeworld
                characterUiModel?.speciesName = (response as SpeciesResponse).name
                getPlanets()
            }
            is PlanetsResponse -> {
                characterUiModel?.population = (response as PlanetsResponse).population?.toLong()!!
                characterLiveData.value = characterUiModel
            }
        }
    }

    private fun extractId(url: String?): Int {
        var lastStr = url?.substring(url.lastIndexOf('/') - 1)
        var id = lastStr?.replace("/", "")
        return id?.toInt()!!
    }
}

