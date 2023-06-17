package com.example.pokeapi.ui.PokemonList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapi.databinding.FragmentPokemonListBinding
import com.example.pokeapi.ui.PokemonList.PokemonListRecView.PokemonListAdapter
import com.example.pokeapi.ui.viewModel.DataState
import com.example.pokeapi.ui.viewModel.PokemonListViewModel

class PokemonListFragment : Fragment() {

    private lateinit var binding: FragmentPokemonListBinding
    private val viewModel: PokemonListViewModel by viewModels()
    private val adapter: PokemonListAdapter = PokemonListAdapter(emptyList()) {}

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
                    // Actualiza la vista para indicar que se está cargando
                }
                is DataState.Error -> {
                    // Actualiza la vista para indicar que se produjo un error
                }
                is DataState.Success -> {
                    // Actualiza la vista con la lista de Pokemon
                    adapter.updateList(dataState.pokemonList)
                    binding.editTextTextFilter.addTextChangedListener { userFilter ->
                        val filteredPokemon = dataState.pokemonList.filter { pokemon ->
                            pokemon.name.contains(userFilter.toString())

                        }
                        adapter.updateList(filteredPokemon)
                    }
                }
            }
        }


    }


    private fun recyclerViewConfig() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }



}
