package com.hfad.pokemon

import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import Model.Pokemon
import Common.Common
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

class PokemonDetail : Fragment() {

    lateinit var viewModel: DetailViewModel
    var pokemon_img: ImageView? = null
    var pokemon_name: TextView? = null
    var pokemon_height: TextView? = null
    var pokemon_weight: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        //Inflate the layout for this fragment
        val itemView = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
        val pokemon: Pokemon?
        //Get position from Argument
        pokemon = if (requireArguments()["num"] == null) {
            Common.commonPokemonList[requireArguments().getInt("position")]
        } else null
        pokemon_img = itemView.findViewById<View>(R.id.pokemon_image) as ImageView
        pokemon_name = itemView.findViewById<View>(R.id.name) as TextView
        pokemon_height = itemView.findViewById<View>(R.id.height) as TextView
        pokemon_weight = itemView.findViewById<View>(R.id.weight) as TextView
        setDetailPokemon(pokemon)
        return itemView
    }

    private fun setDetailPokemon(pokemon: Pokemon?) {
        //Load image
        Glide.with(requireActivity()).load(pokemon!!.img).into(pokemon_img!!)
        pokemon_name!!.text = pokemon.name
        pokemon_weight!!.text = "Weight: " + pokemon.weight
        pokemon_height!!.text = "Height: " + pokemon.height
    }

    companion object {
        var instance: PokemonDetail? = null
        @JvmName("getInstance1")
        @JvmStatic
        fun getInstance(viewModel: DetailViewModel): PokemonDetail? {
            if (instance == null)
                Log.d("detail", "getInstance: $instance")
                instance = PokemonDetail()
            return instance
        }
    }
}