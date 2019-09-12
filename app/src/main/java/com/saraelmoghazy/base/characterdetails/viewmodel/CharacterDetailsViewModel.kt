package com.saraelmoghazy.base.characterdetails.viewmodel

import androidx.lifecycle.MutableLiveData
import com.saraelmoghazy.base.R
import com.saraelmoghazy.base.baselayer.BaseViewModel
import com.saraelmoghazy.base.baselayer.UseCase
import com.saraelmoghazy.base.characterdetails.model.CharacterUiModel
import com.saraelmoghazy.base.characterdetails.model.FilmsResponse
import com.saraelmoghazy.base.characterdetails.model.PlanetsResponse
import com.saraelmoghazy.base.characterdetails.model.SpeciesResponse
import com.saraelmoghazy.base.characterdetails.usecase.FilmsUseCase
import com.saraelmoghazy.base.characterdetails.usecase.PlanetsUseCase
import com.saraelmoghazy.base.characterdetails.usecase.SpeciesUseCase
import com.saraelmoghazy.base.searchpeople.model.ResultsItem


class CharacterDetailsViewModel(
    @field:UseCase(R.id.SpeciesUseCase) val speciesUseCase: SpeciesUseCase, @field:UseCase(
        R.id.PlanetsUseCase
    ) val planetsUseCase: PlanetsUseCase, @field:UseCase(
        R.id.FilmsUseCase
    ) val filmsUseCase: FilmsUseCase
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
           val  speciesId  = extractId(character.species?.get(0))
            getSpecies(speciesId)
        }
    }

    private fun getSpecies(speciesId: Int) {
        speciesUseCase.speciesId = speciesId
        loadingIndicator.isLoading = true
        loadingIndicatorLiveData.value = loadingIndicator
        executeUseCase(speciesUseCase)
    }

    private fun getPlanet(planetId: Int) {
        planetsUseCase.planetId = planetId
        executeUseCase(planetsUseCase)
    }

    override fun <M> onSuccess(response: M?) {
        when (response) {
            is SpeciesResponse -> {
                characterUiModel?.apply {
                    speciesLanguage = response.language
                    speciesName = response.name
                }
                val speciesPlanet = extractId(response.homeworld)
                getPlanet(speciesPlanet)

            }
            is PlanetsResponse -> {
                characterUiModel?.population = response.population?.toLong()!!
                characterUiModel?.speciesHomeWorld = response.name
                val films = ArrayList<Int>()
                for (film in character.films!!) {
                    films.add(extractId(film))
                }
                filmsUseCase.filmIds = films
                executeUseCase(filmsUseCase)
            }
            is FilmsResponse -> {
                characterUiModel?.filmResponse = response.films
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

