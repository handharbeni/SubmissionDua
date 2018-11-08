package com.mhandharbeni.submissiondua

import com.google.gson.Gson
import com.mhandharbeni.submissiondua.model.EventsItem
import com.mhandharbeni.submissiondua.model.Response
import com.mhandharbeni.submissiondua.presenter.MainPresenter
import com.mhandharbeni.submissiondua.tools.ApiRepository
import com.mhandharbeni.submissiondua.tools.MainView
import com.mhandharbeni.submissiondua.tools.TheSportDBApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainPresenterTest {
    @Mock
    private lateinit var view: MainView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    private lateinit var presenter: MainPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view, apiRepository, gson, CoroutineContextProviderTest())
    }

    @Test
    fun getMatchList(){
        val events:MutableList<EventsItem> = mutableListOf()
        val response = Response(events)
        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getFixtures(BuildConfig.NEXT, "4328")),
                Response::class.java
        )).thenReturn(response)

        presenter.getFixturesList(BuildConfig.NEXT, "4328")

        verify(view).showLoading()
        verify(view).showFixtures(events)
    }

}