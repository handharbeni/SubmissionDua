package com.mhandharbeni.submissiondua.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.mhandharbeni.submissiondua.R
import com.mhandharbeni.submissiondua.fragment.ui.FixtureUI
import com.mhandharbeni.submissiondua.fragment.ui.TeamUI
import com.mhandharbeni.submissiondua.model.TeamsItem
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class TeamAdapter (private val teams: List<TeamsItem>, private val clickListener: (TeamsItem?) -> Unit) : RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TeamUI<ViewGroup>().createView(AnkoContext.create(parent.context, parent)))
    }
    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(teams[position], clickListener)
    }

}
class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val ItemCvParent: CardView = view.find(R.id.ItemCvParent)
    private val ItemTxtNama: TextView = view.find(R.id.ItemTxtNama)
    private val ItemIvLogo: ImageView = view.find(R.id.ItemIvLogo)

    fun bindItem(teams: TeamsItem?, clickListener: (TeamsItem?) -> Unit) {
        ItemTxtNama.text = teams?.strTeam
        Picasso.get().load(teams?.strTeamBadge).into(ItemIvLogo)
        ItemCvParent.setOnClickListener {
            clickListener(teams)
        }
    }
}