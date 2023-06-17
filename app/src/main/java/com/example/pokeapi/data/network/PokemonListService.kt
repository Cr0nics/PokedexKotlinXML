package com.example.pokeapi.data.network

import androidx.lifecycle.viewModelScope
import com.example.pokeapi.core.RetrofitHelper
import com.example.pokeapi.data.model.DetailModel.PokeModelDetails
import com.example.pokeapi.data.model.PokemonListModel
import com.example.pokeapi.data.model.ResultPokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.create

class PokemonListService {

    suspend fun getPokemons(): List<ResultPokemon> {
        return withContext(Dispatchers.IO) {
            val response = RetrofitHelper.getRetrofit().create(PokemonAPI::class.java).getListPokemon()
            response.body()?.results ?: emptyList()
        }
    }

    suspend fun getPokemonDetail(pokemonName: String): PokeModelDetails {

        return withContext(Dispatchers.IO) {
            val pokemonDetail = RetrofitHelper.getRetrofit().create(PokemonAPI::class.java)
                .getPokemonDetails(pokemonName)
            pokemonDetail
        }

    }


}
