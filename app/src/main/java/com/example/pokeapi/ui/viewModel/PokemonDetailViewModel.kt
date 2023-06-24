package com.example.pokeapi.ui.viewModel

import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.data.model.ConsumedModel.PokeItemDetails
import com.example.pokeapi.data.model.ConsumedModel.PokeListItem
import com.example.pokeapi.data.model.DetailModel.PokeModelDetails
import com.example.pokeapi.domain.GetPokemonDetailUseCase
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.math.roundToInt

sealed class PokemonState {
    object Loading : PokemonState()
    object Error : PokemonState()
    data class Success(val pokemon: PokeItemDetails) : PokemonState()
}

class PokemonDetailViewModel<Resources> : ViewModel() {
    private val _pokemonState = MutableLiveData<PokemonState>()
    val pokemonState: LiveData<PokemonState> = _pokemonState



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

    fun statsOnUi(view: View,stat: Int){

        val dpValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            ((stat*140)/255).toFloat(),
            view.resources.displayMetrics
        ).roundToInt()

        Log.i("Juaking","$stat, $dpValue")
        val layoutParams: ViewGroup.LayoutParams = view.layoutParams
        layoutParams.height = (dpValue)
        view.layoutParams = layoutParams
    }

}
