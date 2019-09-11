package com.saraelmoghazy.base.searchpeople.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saraelmoghazy.base.searchpeople.usecase.SearchPeopleUseCase

/**
 * Created by Sara Elmoghazy.
 */
class PeopleViewModelFactory(private val searchPeopleUseCase: SearchPeopleUseCase) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PeopleViewModel(searchPeopleUseCase) as T
    }
}