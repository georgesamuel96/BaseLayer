package com.saraelmoghazy.base.characterdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saraelmoghazy.base.characterdetails.usecase.FilmsUseCase
import com.saraelmoghazy.base.characterdetails.usecase.PlanetsUseCase
import com.saraelmoghazy.base.characterdetails.usecase.SpeciesUseCase

/**
 * Created by Sara Elmoghazy.
 */
class CharacterDetailsViewModelFactory(
    private val speciesUseCase: SpeciesUseCase,
    private val planetsUseCase: PlanetsUseCase,
    private val filmsUseCase: FilmsUseCase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharacterDetailsViewModel(speciesUseCase, planetsUseCase,filmsUseCase) as T
    }
}