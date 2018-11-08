package com.mhandharbeni.submissiondua.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.mhandharbeni.submissiondua.R
import com.mhandharbeni.submissiondua.fragment.ui.TeamUI
import com.mhandharbeni.submissiondua.model.sqlite.TeamFavouriteTable
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class FavouriteTeamAdapter(private val favourite: List<TeamFavouriteTable>, private val clickListener: (TeamFavouriteTable?) -> Unit) : RecyclerView.Adapter<TeamFavViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamFavViewHolder {
        return TeamFavViewHolder(TeamUI<ViewGroup>().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: TeamFavViewHolder, position: Int) {
        holder.bindItem(favourite[position], clickListener)
    }


    override fun getItemCount(): Int = favourite.size

}
class TeamFavViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val itemCvParent: CardView = view.find(R.id.ItemCvParent)
    private val itemTxtNama: TextView = view.find(R.id.ItemTxtNama)
    private val itemIvLogo: ImageView = view.find(R.id.ItemIvLogo)

    fun bindItem(teams: TeamFavouriteTable?, clickListener: (TeamFavouriteTable?) -> Unit) {
//        Picasso.get().load(teams.teamBadge).into(teamBadge)
        itemTxtNama.text = teams?.titleTeam
        Picasso.get().load(teams?.logoTeam).into(itemIvLogo)
        itemCvParent.setOnClickListener {
            clickListener(teams)
        }
    }
}