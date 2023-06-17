package com.example.pokeapi.data.model.ConsumedModel

import com.example.pokeapi.data.model.ResultPokemon
import java.util.*

class PokeListItem(
    val id: Int,
    val name: String,
    val img: String
){
    val formatId = "N° ${id.toString().padStart(3,'0')}"
}

private const val URL_RAW = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"

fun ResultPokemon.toDomain(): PokeListItem {
    val arrayUrl = url.split("/")
    val id = arrayUrl[arrayUrl.size - 2].toInt()
    val name = name.replaceFirstChar {
        // cada nombre con Mayuscula
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
    val img = "$URL_RAW$id.png"
    return PokeListItem(id, name, img)
}