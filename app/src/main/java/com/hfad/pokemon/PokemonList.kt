package com.hfad.pokemon

import Retrofit.IPokemonDex
import io.reactivex.disposables.CompositeDisposable
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import Common.ItemOffsetDecoration
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import Common.Common
import Adapter.PokemonListAdapter
import Retrofit.RetrofitClient
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class PokemonList : Fragment() {
    lateinit var iPokemonDex: IPokemonDex
    lateinit var viewModel: ListViewModel
    var compositeDisposable = CompositeDisposable()
    var pokemon_list_recyclerview: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        pokemon_list_recyclerview =
            view.findViewById<View>(R.id.pokemon_list_recyclerview) as RecyclerView
        pokemon_list_recyclerview!!.setHasFixedSize(true)
        pokemon_list_recyclerview!!.layoutManager = GridLayoutManager(activity, 2)
        val itemOffsetDecoration = ItemOffsetDecoration(requireActivity(), R.dimen.spacing)
        pokemon_list_recyclerview!!.addItemDecoration(itemOffsetDecoration)
        fetchData(viewModel)
        return view
    }

    private fun fetchData(viewModel: ListViewModel) {
        iPokemonDex.listPokemon?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.let {
                compositeDisposable.add(
                it.subscribe { pokedex ->
                    Common.commonPokemonList = pokedex?.pokemon!!
                    val adapter = activity?.let { PokemonListAdapter(it, Common.commonPokemonList) }
                    pokemon_list_recyclerview!!.adapter = adapter
                })
            }
    }

    companion object {
        var instance: PokemonList? = null
        @JvmName("getInstance1")
        fun getInstance(): PokemonList? {
            if (instance == null) instance = PokemonList()
            return instance
        }
    }

    init {
        val retrofit = RetrofitClient.instace
        if (retrofit != null) {
            iPokemonDex = retrofit.create(IPokemonDex::class.java)
        }
    }
}