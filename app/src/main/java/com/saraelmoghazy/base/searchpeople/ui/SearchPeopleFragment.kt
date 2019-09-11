package com.saraelmoghazy.base.searchpeople.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saraelmoghazy.base.R
import com.saraelmoghazy.base.baselayer.BaseActivity
import com.saraelmoghazy.base.baselayer.BaseFragment
import com.saraelmoghazy.base.searchpeople.model.ResultsItem
import com.saraelmoghazy.base.searchpeople.viewmodel.PeopleViewModel
import com.saraelmoghazy.base.searchpeople.viewmodel.PeopleViewModelFactory
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.search_people_fragment.view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.kodein
import org.kodein.di.generic.instance


/**
 * Created by Sara Elmoghazy.
 */
class SearchPeopleFragment : BaseFragment<PeopleViewModel>(), KodeinAware {

    companion object {
        val TAG = SearchPeopleFragment.javaClass.simpleName
    }

    private var searchView: SearchView? = null
    private lateinit var queryTextListener: OnQueryTextListener
    override val kodein: Kodein by kodein()
    private val peopleViewModelFactory: PeopleViewModelFactory by instance()
    private val peopleViewModel: PeopleViewModel by lazy {
        ViewModelProvider(this, peopleViewModelFactory).get(PeopleViewModel::class.java)
    }
    private val onItemClickListener: Subject<ResultsItem> = PublishSubject.create()
    private var peopleAdapter: PeopleAdapter = PeopleAdapter(ArrayList(), onItemClickListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewInflated() {
        initPeopleRV()
        observeLiveData()
    }

    override fun getViewModel(): PeopleViewModel = peopleViewModel


    private fun initPeopleRV() {
        onItemClickListener.subscribe(Consumer { t -> })
        rootView.rvPeople.layoutManager =
            LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        rootView.rvPeople.adapter = peopleAdapter
    }

    override val layoutId: Int
        get() = R.layout.search_people_fragment

    private fun observeLiveData() {
        peopleViewModel.apply {
            peopleLiveData.observe(this@SearchPeopleFragment, Observer { peopleResponse ->
                if (peopleResponse.results.isNotEmpty())
                    peopleAdapter.updateRV(peopleResponse.results)
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = searchItem.actionView as SearchView
        searchManager.getSearchableInfo(activity?.componentName)
        queryTextListener = OnQueryChangeListener()
        searchView?.setOnQueryTextListener(queryTextListener)

    }

    fun navigateToDetails() {
        (activity as BaseActivity).navigateToFragment()
    }

    inner class OnQueryChangeListener : OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            peopleViewModel.searchPeople(query)

            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return true
        }
    }

}