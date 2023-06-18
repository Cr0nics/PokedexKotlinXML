package com.example.pokeapi.ui.PokemonFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokeapi.R
import com.example.pokeapi.ui.viewModel.PokemonDetailsViewModel

class PokemonDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = PokemonDetailsFragment()
    }

    private lateinit var viewModel: PokemonDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokemonDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}