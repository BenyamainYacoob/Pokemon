package Adapter

import Model.Pokemon
import androidx.recyclerview.widget.RecyclerView
import Adapter.PokemonListAdapter.MyViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import com.hfad.pokemon.R
import com.bumptech.glide.Glide
import Interface.ItemClickListener
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import android.content.Intent
import Common.Common
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class PokemonListAdapter(var context: Context, var pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.pokemon_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        //Load image
        Glide.with(context).load(pokemonList[position].img).into(holder.pokemon_image)
        //Set name
        holder.pokemon_name.text = pokemonList[position].name

        //Event
        holder.setItemClickListener(object : ItemClickListener {
            override fun onClick(view: View?, position: Int) {
                LocalBroadcastManager.getInstance(context)
                    .sendBroadcast(Intent(Common.KEY_ENABLE_HOME).putExtra("position", position))
            }
        })
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var pokemon_image: ImageView
        var pokemon_name: TextView
        var itemClickListener: ItemClickListener? = null
        @JvmName("setItemClickListener1")
        fun setItemClickListener(itemClickListener: ItemClickListener?) {
            this.itemClickListener = itemClickListener
        }

        override fun onClick(view: View) {
            itemClickListener!!.onClick(view, adapterPosition)
        }

        init {
            pokemon_image = itemView.findViewById<View>(R.id.pokemon_image) as ImageView
            pokemon_name = itemView.findViewById<View>(R.id.txt_pokemon_name) as TextView
            itemView.setOnClickListener(this)
        }
    }
}