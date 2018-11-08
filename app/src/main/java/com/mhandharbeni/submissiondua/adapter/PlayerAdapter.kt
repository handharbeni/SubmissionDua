package com.mhandharbeni.submissiondua.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.mhandharbeni.submissiondua.R
import com.mhandharbeni.submissiondua.fragment.ui.PlayerUI
import com.mhandharbeni.submissiondua.model.PlayerItem
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class PlayerAdapter (private val players: List<PlayerItem>, private val clickListener: (PlayerItem?) -> Unit) : RecyclerView.Adapter<PlayerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(PlayerUI<ViewGroup>().createView(AnkoContext.create(parent.context, parent)))
    }
    override fun getItemCount(): Int {
      return players.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players[position], clickListener)

    }

}
class PlayerViewHolder(view: View) :RecyclerView.ViewHolder(view){
    private val itemCvParent: CardView = view.find(R.id.ItemCvParent)
    private val itemTxtNama: TextView = view.find(R.id.ItemTxtNama)
    private val itemIvLogo: ImageView = view.find(R.id.ItemIvLogo)

    fun bindItem(players: PlayerItem?, clickListener: (PlayerItem?) -> Unit) {
        itemTxtNama.text = players?.namePlayer
        Picasso.get().load(players?.profil).into(itemIvLogo)
        itemCvParent.setOnClickListener {
            clickListener(players)
        }
    }
}