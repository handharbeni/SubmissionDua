package com.mhandharbeni.submissiondua

import com.mhandharbeni.submissiondua.tools.ApiRepository
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ApiRepositoryTest {
    @Test
    fun testDoRequestNextMatch() {
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4335"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }

    @Test
    fun testDoRequestLastMatch() {
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4335"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }

    @Test
    fun testDoRequestListPlayer() {
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/lookup_all_players.php?id=133604"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }
}