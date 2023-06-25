package com.example.pokeapi.ui.PokemonList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapi.R
import com.example.pokeapi.data.model.ConsumedModel.PokeListItem
import com.example.pokeapi.databinding.FragmentPokemonListBinding
import com.example.pokeapi.ui.PokemonFragment.PokemonDetailFragment
import com.example.pokeapi.ui.PokemonList.PokemonListRecView.PokemonListAdapter
import com.example.pokeapi.ui.viewModel.DataState
import com.example.pokeapi.ui.viewModel.PokemonListViewModel
import com.example.pokeapi.ui.viewModel.PokemonState

class PokemonListFragment : Fragment() {

    private lateinit var binding: FragmentPokemonListBinding
    private val viewModel: PokemonListViewModel by viewModels()
    private val adapter: PokemonListAdapter = PokemonListAdapter(emptyList()) {view:View,pokemon: PokeListItem ->
        navigate(view,pokemon)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllPokemons()
        recyclerViewConfig()

        viewModel.dataState.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Loading -> {

                }
                is DataState.Error -> {
                    // Actualiza la vista para indicar que se produjo un error
                }
                is DataState.Success -> {

                    binding.progressBar.isVisible = false
                    // Actualiza la vista con la lista de Pokemon
                    adapter.updateList(dataState.pokemonList)
                    binding.editTextTextFilter.addTextChangedListener { userFilter ->
                        adapter.updateList(
                            viewModel.filteredPokemons(
                                userFilter.toString(),
                                dataState.pokemonList
                            )
                        )
                    }
                }

            }
        }


    }


    private fun recyclerViewConfig() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }
    //pokemon:PokeListItem


    private fun navigate(view: View,pokemon:PokeListItem) {

        val bundle = Bundle()
        bundle.putString("texto", pokemon.name)
        val pokemonDetailFragment = PokemonDetailFragment()
        pokemonDetailFragment.arguments = bundle
        findNavController().navigate(R.id.action_pokemonListFragment_to_pokemonDetailFragment, bundle)
    }

    companion object {
        fun newInstance() = PokemonDetailFragment()
    }

}
