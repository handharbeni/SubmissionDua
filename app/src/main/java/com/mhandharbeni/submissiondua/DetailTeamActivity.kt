package com.mhandharbeni.submissiondua

import android.app.Activity
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.mhandharbeni.submissiondua.adapter.TeamPagerAdapter
import com.mhandharbeni.submissiondua.model.EventsItem
import com.mhandharbeni.submissiondua.model.PlayerItem
import com.mhandharbeni.submissiondua.model.TeamsItem
import com.mhandharbeni.submissiondua.model.sqlite.SqliteFavourite
import com.mhandharbeni.submissiondua.model.sqlite.TeamFavouriteTable
import com.mhandharbeni.submissiondua.presenter.MainPresenter
import com.mhandharbeni.submissiondua.tools.ApiRepository
import com.mhandharbeni.submissiondua.tools.MainView
import com.mhandharbeni.submissiondua.ui.DetailTeamActivityUI
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.*
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.setContentView

class DetailTeamActivity : AppCompatActivity(), MainView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showFixtures(data: List<EventsItem>?) {
    }

    override fun showLastFixtures(data: List<EventsItem>?) {
    }

    override fun showDetail(data: List<TeamsItem>?, status: String) {
    }

    override fun showTeams(data: List<TeamsItem>?) {
        teamsItem = TeamsItem(data?.get(0)?.idTeam, data?.get(0)?.strTeam, data?.get(0)?.strTeamBadge,
                data?.get(0)?.intFormedYear, data?.get(0)?.strStadium, data?.get(0)?.strDescriptionEN)
        initDataFromTeam(teamsItem)
    }

    override fun showPlayer(data: List<PlayerItem>?) {
    }

    private val Context.database : SqliteFavourite get() = SqliteFavourite.getInstance(applicationContext)

    private lateinit var request: ApiRepository
    private lateinit var gson: Gson
    private var presenter: MainPresenter? = null

    private lateinit var idTeam:String
    private var isFav: Boolean = false

    private lateinit var ivLogoTeam: ImageView
    private lateinit var itemTxtNama: TextView
    private lateinit var itemTxtTahun : TextView
    private lateinit var itemTxtStadium: TextView
    private lateinit var tabs: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var frameTeam: FrameLayout

    private lateinit var btnFavorite: FloatingActionButton
    private lateinit var teamsItem: TeamsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        DetailTeamActivityUI<DetailTeamActivity>().setContentView(this)
        setContentView(R.layout.activity_team_detail)
        initModule()
        initView()
        getFavourite()
    }

    private fun initModule(){
        request = ApiRepository()
        gson = Gson()

        idTeam = intent.getStringExtra("idTeam")

        presenter = MainPresenter(this, request, gson)
        presenter?.getDetailTeam(idTeam)
    }

    private fun initView(){
        ivLogoTeam = find(R.id.ivLogoTeam)
        itemTxtNama = find(R.id.ItemTxtNama)
        itemTxtTahun = find(R.id.ItemTxtTahun)
        itemTxtStadium = find(R.id.ItemTxtStadium)
        tabs = find(R.id.tabTeam)
        viewPager = find(R.id.viewPager)
        frameTeam = find(R.id.frameTeam)
        btnFavorite = find(R.id.btnFavourite)

        val fragmentAdapter = TeamPagerAdapter(supportFragmentManager)

        viewPager.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewPager)

        btnFavorite.onClick {
            clickFavourite()
        }
    }

    private fun getFavourite(){
        if (favoriteState()){
            btnFavorite.setImageResource(R.drawable.ic_favourite)
        }else{
            btnFavorite.setImageResource(R.drawable.ic_unfavourite)
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
            val result = select(TeamFavouriteTable.TABLE_NAME)
                    .whereArgs("(ID_TEAM = {id})",
                            "id" to idTeam)
            val favorite = result.parseList(classParser<TeamFavouriteTable>())
            if (!favorite.isEmpty())
                isFav = true
        }
        return isFav
    }

    private fun setFavourite() {
        try {
            database.use {
                insert(TeamFavouriteTable.TABLE_NAME,
                        TeamFavouriteTable.FIELD_ID_TEAM to "${teamsItem.idTeam}",
                        TeamFavouriteTable.FIELD_TITLE_TEAM to "${teamsItem.strTeam}",
                        TeamFavouriteTable.FIELD_LOGO_TEAM to "${teamsItem.strTeamBadge}",
                        TeamFavouriteTable.FIELD_FORMED_YEAR to "${teamsItem.intFormedYear}",
                        TeamFavouriteTable.FIELD_STADIUM to "${teamsItem.strStadium}",
                        TeamFavouriteTable.FIELD_DESKRIPSI to "${teamsItem.strDescriptionEN}" )
            }
        } catch (e: SQLiteConstraintException){

        }
    }

    private fun setUnFavourite(){
        try {
            database.use {
                delete(TeamFavouriteTable.TABLE_NAME, "(ID_TEAM = {id})", "id" to idTeam)
            }
        } catch (e: SQLiteConstraintException){

        }
    }

    private fun initDataFromTeam(teamsItem: TeamsItem){
        Picasso.get().load(teamsItem.strTeamBadge).into(ivLogoTeam)
        itemTxtNama.text = teamsItem.strTeam
        itemTxtTahun.text = teamsItem.intFormedYear
        itemTxtStadium.text = teamsItem.strStadium

    }
}
