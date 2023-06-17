package com.example.pokeapi.data.model.DetailModel

import com.google.gson.annotations.SerializedName
data class PokeModelDetails(
    @SerializedName("name") val name: String,
    @SerializedName("sprites") val sprites: Sprites,

    // falta:
    // - debelidades -> Array
)

data class Sprites(
    @SerializedName("other") val other: Other
)

data class Other(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork
)

data class OfficialArtwork(
    @SerializedName("front_default") val img: String,
)

data class Stats(
    @SerializedName("base_stat") val statValue: Int,
    @SerializedName("stat") val stat: Stat
)

data class Stat(
    @SerializedName("name") val statName: String
)

data class Types(
    @SerializedName("type") val type: Type
)

data class Type(
    @SerializedName("name") val name: String
)