package com.example.pokeapi.data.model

data class PokemonListModel(
    val results: List<ResultPokemon>
)

data class ResultPokemon(
    val name: String,
    val url: String
)