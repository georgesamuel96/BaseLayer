package com.saraelmoghazy.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saraelmoghazy.base.usecase.StarWarUseCase

/**
 * Created by Sara Elmoghazy.
 */
class StarWarsViewModelFactory(private val starWarUseCase: StarWarUseCase) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StarWarViewModel(starWarUseCase) as T
    }
}