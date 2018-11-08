package com.mhandharbeni.submissiondua.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mhandharbeni.submissiondua.R
import com.mhandharbeni.submissiondua.fragment.ui.FixtureUI
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
    private val itemCvParent: CardView = view.find(R.id.ItemCvParent)
    private val itemTxtTanggal: TextView = view.find(R.id.ItemTxtTanggal)
    private val itemTxtHome: TextView = view.find(R.id.ItemTxtHome)
    private val itemTxtHomeScore: TextView = view.find(R.id.ItemTxtHomeScore)
    private val itemTxtAway: TextView = view.find(R.id.ItemTxtAway)
    private val itemTxtAwayScore: TextView = view.find(R.id.ItemTxtAwayScore)

    fun bindItem(teams: FavouriteTable?, clickListener: (FavouriteTable?) -> Unit) {
//        Picasso.get().load(teams.teamBadge).into(teamBadge)
        itemTxtTanggal.text = teams?.date
        itemTxtHome.text = teams?.titleHome
        itemTxtAway.text = teams?.titleAway
        if (!teams?.scoreHome.equals("null")){
            itemTxtHomeScore.text = teams?.scoreHome
            itemTxtAwayScore.text = teams?.scoreAway
        } else {
            itemTxtHomeScore.text = ""
            itemTxtAwayScore.text = ""
        }
        itemCvParent.setOnClickListener {
            clickListener(teams)
        }
    }
}