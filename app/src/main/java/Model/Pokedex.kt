package Model

import Model.Pokemon

class Pokedex {
    var pokemon: List<Pokemon>? = null

    constructor() {}
    constructor(pokemon: List<Pokemon>?) {
        this.pokemon = pokemon
    }
}