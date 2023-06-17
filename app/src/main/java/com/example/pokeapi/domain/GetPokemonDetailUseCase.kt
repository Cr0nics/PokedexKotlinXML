package com.example.pokeapi.domain

import com.example.pokeapi.data.PokemonListRepository
import com.example.pokeapi.data.model.DetailModel.PokeModelDetails
import com.example.pokeapi.data.model.PokemonListModel

class GetPokemonDetailUseCase {
    private val repository = PokemonListRepository()

    suspend operator fun invoke(pokemonName: String): PokeModelDetails {
        return repository.getPokemonDetail(pokemonName)
    }
}