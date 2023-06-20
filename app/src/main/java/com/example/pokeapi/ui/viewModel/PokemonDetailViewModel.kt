package com.example.pokeapi.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.data.model.ConsumedModel.PokeListItem
import com.example.pokeapi.data.model.DetailModel.PokeModelDetails
import com.example.pokeapi.domain.GetPokemonDetailUseCase
import kotlinx.coroutines.launch
import java.io.IOException

sealed class PokemonState {
    object Loading : PokemonState()
    object Error : PokemonState()
    data class Success(val pokemon: PokeModelDetails) : PokemonState()
}
class PokemonDetailViewModel : ViewModel() {
    private val _pokemonState = MutableLiveData<PokemonState>()
    val pokemonState: LiveData<PokemonState> = _pokemonState

    fun getPokemonDetails(pokemonName: String) {


            _pokemonState.postValue(PokemonState.Loading)
            viewModelScope.launch {
                try {
                    val pokemon = GetPokemonDetailUseCase().invoke(pokemonName)
                    _pokemonState.postValue(PokemonState.Success(pokemon))
                    Log.i("Joaking", "$_pokemonState")
                } catch (e: IOException) {
                    _pokemonState.postValue(PokemonState.Error)
                    Log.e("Joaking", "error")
                }
            }
        }

    fun getPokemonDetail(pokemonName: String) {
        viewModelScope.launch {
            _pokemonState.postValue(PokemonState.Loading)
            try {
                val pokemon = GetPokemonDetailUseCase().invoke(pokemonName)
                Log.i("JoaKing", pokemon.name)
                _pokemonState.postValue(PokemonState.Success(pokemon))
            } catch (e: IOException) {
                _pokemonState.postValue(PokemonState.Error)
                Log.e("Joaking", "error")
            }
        }


    }



    }
