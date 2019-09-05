package com.saraelmoghazy.base

import android.app.Application
import com.saraelmoghazy.base.data.remote.RetrofitProvider
import com.saraelmoghazy.base.data.remote.StarWarsRemoteDataSource
import com.saraelmoghazy.base.usecase.StarWarUseCase
import com.saraelmoghazy.base.viewmodel.StarWarsViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

/**
 * Created by Sara Elmoghazy.
 */
class StarWarApp : Application(), KodeinAware {

    private val retrofitModule = Kodein.Module(name = "Retrofit") {
        bind<Retrofit>() with singleton { RetrofitProvider.getInstance() }
    }

    override val kodein by Kodein.lazy {
        import(retrofitModule)
        bind() from provider { StarWarsRemoteDataSource(instance()) }
        bind() from provider { StarWarUseCase(R.id.PeopleUseCase, instance()) }
        bind() from provider { StarWarsViewModelFactory(instance()) }
    }
}