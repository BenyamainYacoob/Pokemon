package com.hfad.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfad.pokemon.PokemonDetail.Companion.getInstance
import android.content.BroadcastReceiver
import android.content.Intent
import Common.Common
import android.content.Context
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import android.content.IntentFilter
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    //var viewModel: DetailViewModel = DetailViewModel("", "", "", "")
    var viewModel: DetailViewModel = DetailViewModel()

    var toolbar: Toolbar? = null
    var showDetail: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action.toString() == Common.KEY_ENABLE_HOME) {
                supportActionBar!!.setDisplayHomeAsUpEnabled(true) // Enable Back Button on Toolbar
                supportActionBar!!.setDisplayShowHomeEnabled(true) // Here as well
                val position = intent.getIntExtra("position", -1)
                val bundle = Bundle()
                bundle.putInt("position", position)
                //Replace fragment
                val detailFragment: Fragment? = viewModel?.let { PokemonDetail.getInstance(it) }
                Log.d("main", "onReceive: $detailFragment")
                detailFragment?.let { detail ->

                    detail.arguments = bundle
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.list_pokemon_fragment, detail)
                    fragmentTransaction.addToBackStack("detail")
                    fragmentTransaction.commit()

                }


                //Set Pokemon name for toolbar
                val pokemon = Common.commonPokemonList[position]
                toolbar!!.title = pokemon.name
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar!!.title = "POKEMON LIST"
        setSupportActionBar(toolbar)

        //Register broadcast
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(showDetail, IntentFilter(Common.KEY_ENABLE_HOME))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                toolbar!!.title = "POKEMON LIST"

                //Clear all fragment detail and pop to list fragment
                supportFragmentManager.popBackStack(
                    "detail",
                    FragmentManager.POP_BACK_STACK_INCLUSIVE)
                supportActionBar!!.setDisplayShowHomeEnabled(false)
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            }
            else -> {}
        }
        return true
    }
}