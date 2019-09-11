package com.saraelmoghazy.base.chardetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saraelmoghazy.base.chardetails.usecase.PlanetsUseCase
import com.saraelmoghazy.base.chardetails.usecase.SpeciesUseCase
import com.saraelmoghazy.base.searchpeople.model.ResultsItem
import com.saraelmoghazy.base.searchpeople.usecase.SearchPeopleUseCase

/**
 * Created by Sara Elmoghazy.
 */
class CharacterDetailsViewModelFactory(
    private val speciesUseCase: SpeciesUseCase,
    private val planetsUseCase: PlanetsUseCase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharacterDetailsViewModel(speciesUseCase, planetsUseCase) as T
    }
}