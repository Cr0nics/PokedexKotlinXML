package com.example.pokeapi.ui.PokemonFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapi.R
import com.example.pokeapi.data.model.ConsumedModel.PokeItemDetails
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
        binding.onBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.pokemonState.observe(viewLifecycleOwner) { pokemonState ->
            when (pokemonState) {
                is PokemonState.Loading -> {



                }
                is PokemonState.Error -> {
                    // Actualiza la vista para indicar que se produjo un error
                }
                is PokemonState.Success -> {
                    binding.progressBarDetail.isVisible = false

                    Picasso.get().load(pokemonState.pokemon.img).into(binding.imviPokemon)
                    binding.tvPokemon.text = pokemonState.pokemon.name
                    setStatsOnLayout(pokemonState.pokemon)
                    setTypes(pokemon = pokemonState.pokemon)
                    binding.linearLayout3.isVisible = true
                    binding.linearLayout.isVisible = true


                }

            }
        }


    }

    //pokemon:PokeListItem


    companion object {
        fun newInstance() = PokemonDetailFragment()
    }

    fun setStatsOnLayout(pokemon: PokeItemDetails) {
        viewModel.statsOnUi(view = binding.viewHP, stat = pokemon.hp)
        viewModel.statsOnUi(view = binding.viewDef, stat = pokemon.defense)
        viewModel.statsOnUi(view = binding.viewSpeed, stat = pokemon.speed)
        viewModel.statsOnUi(view = binding.viewAttk, stat = pokemon.attack)
        viewModel.statsOnUi(view = binding.viewSpAttk, stat = pokemon.specialAttack)
        viewModel.statsOnUi(view = binding.viewSpDef, stat = pokemon.specialDefense)
    }


    fun setTypes(pokemon: PokeItemDetails) {

        binding.tvType1.text = pokemon.types[0]
//        binding.tvType1.setBackgroundColor(viewModel.typeColor(pokemon.types[0]))
        binding.tvType1.setBackgroundColor(
            ContextCompat.getColor(requireContext(), viewModel.typeColor(pokemon.types[0]))
        )

        if (pokemon.types.size > 1) {
            binding.tvType2.text = pokemon.types[1]
            binding.tvType2.setBackgroundColor(
                ContextCompat.getColor(requireContext(), viewModel.typeColor(pokemon.types[1]))
            )
        }


    }


}