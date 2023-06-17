package com.example.pokeapi.domain

import com.example.pokeapi.data.PokemonListRepository
import com.example.pokeapi.data.model.ConsumedModel.PokeListItem
import com.example.pokeapi.data.model.PokemonListModel

class GetPokemonListUseCase {

    private val repository = PokemonListRepository()

    suspend operator fun invoke(): List<PokeListItem>{
        return repository.getAllPokemons()
    }

}