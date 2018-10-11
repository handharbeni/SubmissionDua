package com.mhandharbeni.submissiondua.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mhandharbeni.submissiondua.R
import com.mhandharbeni.submissiondua.fragment.ui.FixtureUI
import com.mhandharbeni.submissiondua.model.EventsItem
import com.mhandharbeni.submissiondua.model.sqlite.FavouriteTable
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class FavouriteAdapter(private val favourite: List<FavouriteTable>, private val clickListener: (FavouriteTable?) -> Unit) : RecyclerView.Adapter<FavouriteTeamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteTeamViewHolder {
        return FavouriteTeamViewHolder(FixtureUI<ViewGroup>().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: FavouriteTeamViewHolder, position: Int) {
        holder.bindItem(favourite[position], clickListener)

    }

    override fun getItemCount(): Int = favourite.size

}
class FavouriteTeamViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val ItemCvParent: CardView = view.find(R.id.ItemCvParent)
    private val ItemTxtTanggal: TextView = view.find(R.id.ItemTxtTanggal)
    private val ItemTxtHome: TextView = view.find(R.id.ItemTxtHome)
    private val ItemTxtHomeScore: TextView = view.find(R.id.ItemTxtHomeScore)
    private val ItemTxtAway: TextView = view.find(R.id.ItemTxtAway)
    private val ItemTxtAwayScore: TextView = view.find(R.id.ItemTxtAwayScore)

    fun bindItem(teams: FavouriteTable?, clickListener: (FavouriteTable?) -> Unit) {
//        Picasso.get().load(teams.teamBadge).into(teamBadge)
        ItemTxtTanggal.text = teams?.date
        ItemTxtHome.text = teams?.titleHome
        ItemTxtAway.text = teams?.titleAway
        ItemTxtHomeScore.text = teams?.scoreHome
        ItemTxtAwayScore.text = teams?.scoreAway
        ItemCvParent.setOnClickListener {
            clickListener(teams)
        }
    }
}