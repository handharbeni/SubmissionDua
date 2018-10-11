package com.mhandharbeni.submissiondua

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.mhandharbeni.submissiondua.model.EventsItem
import com.mhandharbeni.submissiondua.model.TeamsItem
import com.mhandharbeni.submissiondua.model.sqlite.FavouriteTable
import com.mhandharbeni.submissiondua.model.sqlite.SqliteFavourite
import com.mhandharbeni.submissiondua.presenter.MainPresenter
import com.mhandharbeni.submissiondua.tools.ApiRepository
import com.mhandharbeni.submissiondua.tools.MainView
import com.mhandharbeni.submissiondua.ui.DetailActivityUI
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.db.*
import org.jetbrains.anko.sdk25.coroutines.onClick


class DetailActivity: AppCompatActivity(), MainView, AnkoLogger{
    override fun showFavourite(data: List<FavouriteTable>?) {
    }

    override fun showDetail(data: List<TeamsItem>?, status: String) {
        when (status) {
            "home" -> {
                Picasso.get().load(data?.get(0)?.strTeamBadge).into(detailImageHome)
            }
            "away" -> {
                Picasso.get().load(data?.get(0)?.strTeamBadge).into(detailImageAway)
            }
        }
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showFixtures(data: List<EventsItem>?) {
    }

    companion object {

        fun newIntent(context: Context, eventsItem: EventsItem?): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("selectedFixtures", eventsItem)
            intent.putExtra("DetailBundle", bundle)
            intent.putExtra("From", "API")
            return intent
        }
        fun newIntent(context: Context, favourite: FavouriteTable?):Intent{
            val intent = Intent(context, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("selectedFixtures", favourite)
            intent.putExtra("DetailBundle", bundle)
            intent.putExtra("From", "SQL")
            return intent
        }
    }

    private val Context.database : SqliteFavourite get() = SqliteFavourite.getInstance(applicationContext)

    private lateinit var request: ApiRepository
    private lateinit var gson: Gson
    private var presenter: MainPresenter? = null

    private lateinit var detailImageHome: ImageView
    private lateinit var detailImageAway: ImageView
    private lateinit var detailTxtHome: TextView
    private lateinit var detailTxtAway: TextView
    private lateinit var detailTxtItemHomeScore: TextView
    private lateinit var detailTxtItemAwayScore: TextView

    private lateinit var detailGoalHome: TextView
    private lateinit var detailGoalAway: TextView

    private lateinit var detailLUGKHome: TextView
    private lateinit var detailLUGKDefendersHome: TextView
    private lateinit var detailLUGKMildfielderHome: TextView
    private lateinit var detailLUGKForwardsHome: TextView
    private lateinit var detailLUGKSubstituesHome: TextView

    private lateinit var detailLUGKAway: TextView
    private lateinit var detailLUGKDefendersAway: TextView
    private lateinit var detailLUGKMildfielderAway: TextView
    private lateinit var detailLUGKForwardsAway: TextView
    private lateinit var detailLUGKSubstituesAway: TextView

    private lateinit var btnFavourite: FloatingActionButton

    private lateinit var from: String
    private lateinit var bundle: Bundle
    private lateinit var eventsItem: EventsItem
    private lateinit var favourite: FavouriteTable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailActivityUI<DetailActivity>().setContentView(this)
        initView()

        initBundle()
        initModule()

        getFavourite()
    }

    private fun initBundle(){
        from = intent.getStringExtra("From")
        bundle = intent.getBundleExtra("DetailBundle")
        if (from.equals("API", true)){
            eventsItem= bundle.getParcelable("selectedFixtures") as EventsItem
            initDataFromEvent()
        }else if(from.equals("SQL", true)){
            favourite = bundle.getParcelable("selectedFixtures") as FavouriteTable
            initDataFromFavourite()
        }
    }

    private fun initModule(){
        request = ApiRepository()
        gson = Gson()

        presenter = MainPresenter(this, request, gson, database)
    }

    private fun initView(){
        detailImageHome = find(R.id.DetailIMGHome)
        detailImageAway = find(R.id.DetailIMGAway)

        detailTxtHome = find(R.id.detailTXTHome)
        detailTxtAway = find(R.id.detailTXTAway)

        detailTxtItemHomeScore = find(R.id.DetailItemTxtHomeScore)
        detailTxtItemAwayScore = find(R.id.DetailItemTxtAwayScore)

        detailGoalHome = find(R.id.detailGoalHome)
        detailGoalAway = find(R.id.detailGoalAway)

        detailLUGKHome = find(R.id.detailLUGKHome)
        detailLUGKDefendersHome = find(R.id.detailLUGKDefendersHome)
        detailLUGKMildfielderHome = find(R.id.detailLUGKMildfielderHome)
        detailLUGKForwardsHome = find(R.id.detailLUGKForwardsHome)
        detailLUGKSubstituesHome = find(R.id.detailLUGKSubstituesHome)

        detailLUGKAway = find(R.id.detailLUGKAway)
        detailLUGKDefendersAway = find(R.id.detailLUGKDefendersAway)
        detailLUGKMildfielderAway = find(R.id.detailLUGKMildfielderAway)
        detailLUGKForwardsAway = find(R.id.detailLUGKForwardsAway)
        detailLUGKSubstituesAway = find(R.id.detailLUGKSubstituesAway)

        btnFavourite = find(R.id.btnFavourite)

        btnFavourite.onClick {
            clickFavourite()
        }
    }
    private fun initDataFromEvent(){
        detailTxtHome.text = eventsItem.strHomeTeam
        detailTxtAway.text = eventsItem.strAwayTeam

        detailTxtItemHomeScore.text = eventsItem.intHomeScore.toString()
        detailTxtItemAwayScore.text = eventsItem.intAwayScore.toString()

        detailGoalHome.text = eventsItem.strHomeGoalDetails?.replace(";", "\n")
        detailGoalAway.text = eventsItem.strAwayGoalDetails?.replace(";", "\n")

        detailLUGKHome.text = eventsItem.strHomeLineupGoalkeeper?.replace(";", "\n")
        detailLUGKDefendersHome.text = eventsItem.strHomeLineupDefense?.replace(";", "\n")
        detailLUGKMildfielderHome.text = eventsItem.strHomeLineupMidfield?.replace(";", "\n")
        detailLUGKForwardsHome.text = eventsItem.strHomeLineupForward?.replace(";", "\n")
        detailLUGKSubstituesHome.text = eventsItem.strHomeLineupSubstitutes?.replace(";", "\n")

        detailLUGKAway.text = eventsItem.strAwayLineupGoalkeeper?.replace(";", "\n")
        detailLUGKDefendersAway.text = eventsItem.strAwayLineupDefense?.replace(";", "\n")
        detailLUGKMildfielderAway.text = eventsItem.strAwayLineupMid?.replace(";", "\n")
        detailLUGKForwardsAway.text = eventsItem.strAwayLineupForward?.replace(";", "\n")
        detailLUGKSubstituesAway.text = eventsItem.strAwayLineupSubstitutes?.replace(";", "\n")

        getHomeTeam()
        getAwayTeam()


    }
    private fun initDataFromFavourite(){

        detailTxtHome.text = favourite.titleHome
        detailTxtAway.text = favourite.titleAway

        detailTxtItemHomeScore.text = favourite.scoreHome
        detailTxtItemAwayScore.text = favourite.scoreAway

    }

    private fun getHomeTeam(){
        presenter?.getTeamDetail(eventsItem.idHomeTeam, "home")
    }

    private fun getAwayTeam(){
        presenter?.getTeamDetail(eventsItem.idAwayTeam, "away")
    }

    private fun getFavourite(){
        if (getColumnCount(true)>0){
            btnFavourite.setImageResource(R.drawable.ic_favourite)
        }else{
            btnFavourite.setImageResource(R.drawable.ic_unfavourite)
        }
    }
    private var countColumn = 0
    private fun getColumnCount(byId: Boolean) :Int{
        val rowParser = classParser<FavouriteTable>()
        database.use {
            if (byId){
                select(FavouriteTable.TABLE_NAME)
                        .whereArgs("${FavouriteTable.FIELD_ID_FIXTURES} = {idFixtures}",
                                "idFixtures" to "${eventsItem.idEvent}").exec {
                            countColumn = parseList(
                                    rowParser
                            ).count()
                        }
            }else{
                select(FavouriteTable.TABLE_NAME)
                        .exec {
                            countColumn = parseList(
                                    rowParser
                            ).count()
                        }
            }
        }
        return countColumn
    }
    var id: Int = 0
    private fun clickFavourite(){
        id = getColumnCount(false)+1
        if (getColumnCount(true) > 0){
            setUnFavourite()
        }else{
            setFavourite()
        }
        getFavourite()
    }

    private fun setFavourite() {
        /*insert*/
        database.use {
            insert(FavouriteTable.TABLE_NAME,
                    FavouriteTable.FIELD_ID_FIXTURES to "${eventsItem.idEvent}",
                    FavouriteTable.FIELD_DATE_FIXTURES to "${eventsItem.strDate}",
                    FavouriteTable.FIELD_TITLE_HOME to "${eventsItem.strHomeTeam}",
                    FavouriteTable.FIELD_TITLE_AWAY to "${eventsItem.strAwayTeam}",
                    FavouriteTable.FIELD_SCORE_HOME to "${eventsItem.intHomeScore}",
                    FavouriteTable.FIELD_SCORE_AWAY to "${eventsItem.intAwayScore}" )
        }
    }

    private fun setUnFavourite(){
        /*delete*/
        database.use {
            return@use delete(FavouriteTable.TABLE_NAME,
                    "${FavouriteTable.FIELD_ID_FIXTURES} = {idDelete}",
                    "idDelete" to "${eventsItem.idEvent}")
        }
    }
}