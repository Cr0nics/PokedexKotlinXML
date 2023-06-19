package com.example.pokeapi.ui.PokemonList.PokemonListRecView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi.R
import com.example.pokeapi.data.model.ConsumedModel.PokeListItem
import com.example.pokeapi.data.model.DetailModel.PokeModelDetails
import com.example.pokeapi.data.model.PokemonListModel
import com.example.pokeapi.data.model.ResultPokemon

class PokemonListAdapter(private var pokemonList: List<PokeListItem>,private val onItemClick: (view:View) -> Unit) :
    RecyclerView.Adapter<PokemonListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.pokemonlistitem, parent, false)
        return PokemonListViewHolder(view)


    }

    fun updateList(newPokemonList: List<PokeListItem>) {
        this.pokemonList = newPokemonList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.bind(pokemon,onItemClick)
    }
}