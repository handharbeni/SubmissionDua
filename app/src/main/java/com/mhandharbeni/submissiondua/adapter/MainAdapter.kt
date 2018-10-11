package com.mhandharbeni.submissiondua.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mhandharbeni.submissiondua.R
import com.mhandharbeni.submissiondua.model.EventsItem
import com.mhandharbeni.submissiondua.fragment.ui.FixtureUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class MainAdapter (private val fixtures: List<EventsItem>, private val clickListener: (EventsItem?) -> Unit) : RecyclerView.Adapter<TeamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(FixtureUI<ViewGroup>().createView(AnkoContext.create(parent.context, parent)))
    }
    override fun getItemCount(): Int = fixtures.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(fixtures[position], clickListener)
    }

}
class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val ItemCvParent: CardView = view.find(R.id.ItemCvParent)
    private val ItemTxtTanggal: TextView = view.find(R.id.ItemTxtTanggal)
    private val ItemTxtHome: TextView = view.find(R.id.ItemTxtHome)
    private val ItemTxtHomeScore: TextView = view.find(R.id.ItemTxtHomeScore)
    private val ItemTxtAway: TextView = view.find(R.id.ItemTxtAway)
    private val ItemTxtAwayScore: TextView = view.find(R.id.ItemTxtAwayScore)

    fun bindItem(teams: EventsItem?, clickListener: (EventsItem?) -> Unit) {
//        Picasso.get().load(teams.teamBadge).into(teamBadge)
        ItemTxtTanggal.text = teams?.strDate
        ItemTxtHome.text = teams?.strHomeTeam
        ItemTxtAway.text = teams?.strAwayTeam
        ItemTxtHomeScore.text = teams?.intHomeScore
        ItemTxtAwayScore.text = teams?.intAwayScore
        ItemCvParent.setOnClickListener {
            clickListener(teams)
        }
    }
}