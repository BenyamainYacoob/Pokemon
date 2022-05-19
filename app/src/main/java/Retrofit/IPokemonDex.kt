package Retrofit

import retrofit2.http.GET
import Model.Pokedex
import Model.Pokemon
import io.reactivex.Observable

interface IPokemonDex {
    @get:GET("pokedex.json")
    val listPokemon: Observable<Pokedex?>?
    val pokemon: List<Pokemon?>?
}