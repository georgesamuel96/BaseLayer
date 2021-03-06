package com.saraelmoghazy.base.characterdetails

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.saraelmoghazy.base.R
import com.saraelmoghazy.base.baselayer.BaseFragment
import com.saraelmoghazy.base.characterdetails.viewmodel.CharacterDetailsViewModel
import com.saraelmoghazy.base.characterdetails.viewmodel.CharacterDetailsViewModelFactory
import com.saraelmoghazy.base.searchpeople.model.ResultsItem
import kotlinx.android.synthetic.main.character_details_fragment.view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.kodein
import org.kodein.di.generic.instance

class CharacterDetailsFragment : BaseFragment<CharacterDetailsViewModel>(), KodeinAware {

    companion object {
        val TAG = CharacterDetailsFragment.javaClass.simpleName
        val CHARACTER = "Character"
        fun newInstance(item: ResultsItem) = CharacterDetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(CHARACTER, item)
            }
        }
    }

    private lateinit var character: ResultsItem
    override val kodein: Kodein by kodein()
    private val characterDetailsViewModelFactory: CharacterDetailsViewModelFactory by instance()
    private val characterDetailsViewModel: CharacterDetailsViewModel by lazy {
        ViewModelProvider(
            this,
            characterDetailsViewModelFactory
        ).get(CharacterDetailsViewModel::class.java)
    }

    override val layoutId: Int
        get() = R.layout.character_details_fragment

    override fun getViewModel(): CharacterDetailsViewModel {
        return characterDetailsViewModel
    }

    override fun getShimmerLayout(): Int {
        return R.layout.partial_loading_details
    }

    override fun onViewInflated() {
        observeCharacterDetails()
        characterDetailsViewModel.getCharacter(character)
    }

    private fun observeCharacterDetails() {
        characterDetailsViewModel.characterLiveData.observe(this, Observer { character ->
            character.run {
                rootView.txtName.text = name
                rootView.txtPopulation.text = population.toString()
                rootView.txtSpeciesHomeWorld.text = speciesHomeWorld
                rootView.txtBirth.text = birthYear
                rootView.txtSpeciesLanguage.text = speciesLanguage
                val sb = StringBuilder()
                filmResponse?.forEach {
                    sb.append(it).append(" / ")
                }
                rootView.txtFilms.text = sb.toString()
                rootView.txtSpeciesName.text = speciesName
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        character = arguments?.getSerializable(CHARACTER) as ResultsItem
    }

}