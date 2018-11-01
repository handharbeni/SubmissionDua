package com.mhandharbeni.submissiondua

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.mhandharbeni.submissiondua.model.EventsItem
import com.mhandharbeni.submissiondua.model.PlayerItem
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
    override fun showTeams(data: List<TeamsItem>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showPlayer(data: List<PlayerItem>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLastFixtures(data: List<EventsItem>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        eventsItem = EventsItem(data?.get(0)?.intHomeShots, data?.get(0)?.strSport, data?.get(0)?.strHomeLineupDefense, data?.get(0)?.strAwayLineupSubstitutes,
                data?.get(0)?.idLeague, data?.get(0)?.idSoccerXML, data?.get(0)?.strHomeLineupForward, data?.get(0)?.strTVStation,
                data?.get(0)?.strHomeGoalDetails, data?.get(0)?.strAwayLineupGoalkeeper, data?.get(0)?.strAwayLineupMid, data?.get(0)?.idEvent,
                data?.get(0)?.intRound, data?.get(0)?.strHomeYellowCards, data?.get(0)?.idHomeTeam, data?.get(0)?.intHomeScore, data?.get(0)?.dateEvent,
                data?.get(0)?.strCountry, data?.get(0)?.strAwayTeam, data?.get(0)?.strHomeLineupMidfield, data?.get(0)?.strDate, data?.get(0)?.strHomeFormation,
                data?.get(0)?.strMap, data?.get(0)?.idAwayTeam, data?.get(0)?.strAwayRedCards, data?.get(0)?.strBanner, data?.get(0)?.strFanart,
                data?.get(0)?.strDescriptionEN, data?.get(0)?.strResult, data?.get(0)?.strCircuit, data?.get(0)?.intAwayShots, data?.get(0)?.strFilename, data?.get(0)?.strTime,
                data?.get(0)?.strAwayGoalDetails, data?.get(0)?.strAwayLineupForward, data?.get(0)?.strLocked, data?.get(0)?.strSeason, data?.get(0)?.intSpectators,
                data?.get(0)?.strHomeRedCards, data?.get(0)?.strHomeLineupGoalkeeper, data?.get(0)?.strHomeLineupSubstitutes, data?.get(0)?.strAwayFormation, data?.get(0)?.strEvent,
                data?.get(0)?.strAwayYellowCards, data?.get(0)?.strAwayLineupDefense, data?.get(0)?.strHomeTeam, data?.get(0)?.strThumb, data?.get(0)?.strLeague,
                data?.get(0)?.intAwayScore, data?.get(0)?.strCity, data?.get(0)?.strPoster)

        initDataFromEvent(eventsItem)
    }

    private val Context.database : SqliteFavourite get() = SqliteFavourite.getInstance(applicationContext)

    private lateinit var request: ApiRepository
    private lateinit var gson: Gson
    private var presenter: MainPresenter? = null

    private lateinit var idEvent:String
    private var isFav: Boolean = false

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

    private lateinit var eventsItem: EventsItem
    private lateinit var favourite: FavouriteTable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailActivityUI<DetailActivity>().setContentView(this)
        initView()
        initModule()
        getFavourite()
    }

    private fun initModule(){
        request = ApiRepository()
        gson = Gson()

        idEvent = intent.getStringExtra("id")

        presenter = MainPresenter(this, request, gson)
        presenter?.getDetailFixtures(idEvent)
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
    private fun initDataFromEvent(eventsItem: EventsItem){
        detailTxtHome.text = eventsItem.strHomeTeam
        detailTxtAway.text = eventsItem.strAwayTeam

        if (!eventsItem.intHomeScore.isNullOrEmpty()){
            detailTxtItemHomeScore.text = eventsItem.intHomeScore.toString()
            detailTxtItemAwayScore.text = eventsItem.intAwayScore.toString()
        } else {
            detailTxtItemHomeScore.text = ""
            detailTxtItemAwayScore.text = ""
        }

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
        if (favoriteState()){
            btnFavourite.setImageResource(R.drawable.ic_favourite)
        }else{
            btnFavourite.setImageResource(R.drawable.ic_unfavourite)
        }
    }

    private fun clickFavourite(){
        if (isFav)
            setUnFavourite()
        else
            setFavourite()
        isFav = !isFav
        getFavourite()
    }

    private fun favoriteState():Boolean{
        database.use {
            val result = select(FavouriteTable.TABLE_NAME)
                    .whereArgs("(ID_FIXTURES = {id})",
                            "id" to idEvent)
            val favorite = result.parseList(classParser<FavouriteTable>())
            if (!favorite.isEmpty())
                isFav = true
        }
        return isFav
    }

    private fun setFavourite() {
        try {
            database.use {
                insert(FavouriteTable.TABLE_NAME,
                        FavouriteTable.FIELD_ID_FIXTURES to "${eventsItem.idEvent}",
                        FavouriteTable.FIELD_DATE_FIXTURES to "${eventsItem.strDate}",
                        FavouriteTable.FIELD_TITLE_HOME to "${eventsItem.strHomeTeam}",
                        FavouriteTable.FIELD_TITLE_AWAY to "${eventsItem.strAwayTeam}",
                        FavouriteTable.FIELD_SCORE_HOME to "${eventsItem.intHomeScore}",
                        FavouriteTable.FIELD_SCORE_AWAY to "${eventsItem.intAwayScore}" )
            }
        } catch (e: SQLiteConstraintException){

        }
    }

    private fun setUnFavourite(){
        try {
            database.use {
                delete(FavouriteTable.TABLE_NAME, "(ID_FIXTURES = {id})", "id" to idEvent)
            }
        } catch (e: SQLiteConstraintException){

        }
    }
}