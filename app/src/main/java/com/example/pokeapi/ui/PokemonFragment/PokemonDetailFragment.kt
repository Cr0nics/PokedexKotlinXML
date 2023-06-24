package com.example.pokeapi.ui.PokemonFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapi.R
import com.example.pokeapi.data.model.ConsumedModel.PokeListItem
import com.example.pokeapi.databinding.FragmentPokemonDetailBinding
import com.example.pokeapi.databinding.FragmentPokemonListBinding
import com.example.pokeapi.ui.PokemonList.PokemonListRecView.PokemonListAdapter
import com.example.pokeapi.ui.viewModel.DataState
import com.example.pokeapi.ui.viewModel.PokemonDetailViewModel
import com.example.pokeapi.ui.viewModel.PokemonListViewModel
import com.example.pokeapi.ui.viewModel.PokemonState
import com.squareup.picasso.Picasso

class PokemonDetailFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDetailBinding
    private val viewModel: PokemonDetailViewModel<Any?> by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val texto: String? = (requireArguments().getString("texto")).toString()
        viewModel.getPokemonDetail("${texto.toString().lowercase()}")

        viewModel.pokemonState.observe(viewLifecycleOwner) { pokemonState ->
            when (pokemonState) {
                is PokemonState.Loading -> {

                }
                is PokemonState.Error -> {
                    // Actualiza la vista para indicar que se produjo un error
                }
                is PokemonState.Success -> {

                    Picasso.get().load(pokemonState.pokemon.img).into(binding.imviPokemon)
                    binding.tvPokemon.text = pokemonState.pokemon.name
                    binding.tvType1.text = pokemonState.pokemon.types[0]
                    if (pokemonState.pokemon.types.size > 1){
                        binding.tvType2.text = pokemonState.pokemon.types[1]
                    }

                    viewModel.statsOnUi(view = binding.viewHP,stat = pokemonState.pokemon.hp)
                    viewModel.statsOnUi(view = binding.viewDef,stat = 140)
                    viewModel.statsOnUi(view = binding.viewSpeed,stat = 70)
                    viewModel.statsOnUi(view = binding.viewAttk,stat = 170)


                }

            }
        }


    }

    //pokemon:PokeListItem


    private fun navigate(view: View, pokemon: PokeListItem) {

        val bundle = Bundle()
        bundle.putString("texto", pokemon.name)
        val pokemonDetailFragment = PokemonDetailFragment()
        pokemonDetailFragment.arguments = bundle
        findNavController().navigate(
            R.id.action_pokemonListFragment_to_pokemonDetailFragment,
            bundle
        )
    }

    companion object {
        fun newInstance() = PokemonDetailFragment()
    }

}