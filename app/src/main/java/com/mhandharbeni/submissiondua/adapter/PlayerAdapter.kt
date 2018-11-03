package com.mhandharbeni.submissiondua.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.mhandharbeni.submissiondua.R
import com.mhandharbeni.submissiondua.fragment.ui.FixtureUI
import com.mhandharbeni.submissiondua.fragment.ui.PlayerUI
import com.mhandharbeni.submissiondua.fragment.ui.TeamUI
import com.mhandharbeni.submissiondua.model.PlayerItem
import com.mhandharbeni.submissiondua.model.TeamsItem
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.info

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
    private val ItemCvParent: CardView = view.find(R.id.ItemCvParent)
    private val ItemTxtNama: TextView = view.find(R.id.ItemTxtNama)
    private val ItemIvLogo: ImageView = view.find(R.id.ItemIvLogo)

    fun bindItem(players: PlayerItem?, clickListener: (PlayerItem?) -> Unit) {
        ItemTxtNama.text = players?.namePlayer
        Picasso.get().load(players?.profil).into(ItemIvLogo)
        ItemCvParent.setOnClickListener {
            clickListener(players)
        }
    }
}