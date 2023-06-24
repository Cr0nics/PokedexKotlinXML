package com.example.pokeapi.ui.viewModel

import android.graphics.Color
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.R
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

    fun typeColor(pokemonType: String):Int {

        return when (pokemonType) {
            "Fire" -> R.color.Fire
            "Water" -> R.color.Water
            "Grass" -> R.color.Grass
            "Electric" -> R.color.Electric
            "Normal" -> R.color.Normal
            "Fighting" -> R.color.Fighting
            "Flying" -> R.color.Flying
            "Poison" -> R.color.Poison
            "Ground" -> R.color.Ground
            "Rock" -> R.color.Rock
            "Bug" -> R.color.Bug
            "Ghost" -> R.color.Ghost
            "Steel" -> R.color.Steel
            "Psychic" -> R.color.Psychic
            "Ice" -> R.color.Ice
            "Dragon" -> R.color.Dragon
            "Dark" -> R.color.Dark
            "Fairy" -> R.color.Fairy
            else -> throw IllegalArgumentException("Invalid Pokemon type: $pokemonType")
        }

    }

    fun statsOnUi(view: View, stat: Int) {

        val dpValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            ((stat * 140) / 255).toFloat(),
            view.resources.displayMetrics
        ).roundToInt()

        val layoutParams: ViewGroup.LayoutParams = view.layoutParams
        layoutParams.height = (dpValue)
        view.layoutParams = layoutParams
    }

}
