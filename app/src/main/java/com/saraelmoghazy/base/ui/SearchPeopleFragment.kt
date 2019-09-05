package com.saraelmoghazy.base.ui

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saraelmoghazy.base.R
import com.saraelmoghazy.base.baselayer.BaseFragment
import com.saraelmoghazy.base.viewmodel.StarWarViewModel
import com.saraelmoghazy.base.viewmodel.StarWarsViewModelFactory
import kotlinx.android.synthetic.main.search_people_fragment.view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.kodein
import org.kodein.di.generic.instance


/**
 * Created by Sara Elmoghazy.
 */
class SearchPeopleFragment : BaseFragment<StarWarViewModel>(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val starWarsViewModelFactory: StarWarsViewModelFactory by instance()
    private val starWarViewModel: StarWarViewModel by lazy {
        ViewModelProvider(this, starWarsViewModelFactory).get(StarWarViewModel::class.java)
    }
    private var peopleAdapter: PeopleAdapter = PeopleAdapter(ArrayList())

    override fun onViewInflated() {
        initPeopleRV()
        observeLiveData()
    }

    override fun getViewModel(): StarWarViewModel = starWarViewModel


    private fun initPeopleRV() {
        rootView.rvPeople.layoutManager =
            LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        rootView.rvPeople.adapter = peopleAdapter
    }

    override val layoutId: Int
        get() = R.layout.search_people_fragment

    private fun observeLiveData() {
        starWarViewModel.apply {
            peopleLiveData.observe(this@SearchPeopleFragment, Observer { peopleResponse ->
                if (peopleResponse.results.isNotEmpty())
                    peopleAdapter.updateRV(peopleResponse.results)
            })
        }
    }
}