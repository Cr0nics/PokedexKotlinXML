package com.example.pokeapi.ui.PokemonList.PokemonListRecView

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi.data.model.ConsumedModel.PokeListItem
import com.example.pokeapi.data.model.DetailModel.PokeModelDetails
import com.example.pokeapi.data.model.PokemonListModel
import com.example.pokeapi.data.model.ResultPokemon
import com.example.pokeapi.databinding.PokemonlistitemBinding
import com.squareup.picasso.Picasso

class PokemonListViewHolder(view:View,) : RecyclerView.ViewHolder(view)  {
    private val binding = PokemonlistitemBinding.bind(view)

    fun bind(pokemon:PokeListItem,onItemClick: (view:View) -> Unit){
        Picasso.get().load(pokemon.img).into(binding.imviPokemonPhoto)
        binding.tvPokemonName.text = pokemon.name
        itemView.setOnClickListener { onItemClick(itemView) }
    }

}