package com.veldic.ingyeogg.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.veldic.ingyeogg.models.SummonerDto
import com.veldic.ingyeogg.network.service.MatchService
import com.veldic.ingyeogg.network.service.SummonerService

class MainViewModel @ViewModelInject constructor(
    private val summonerService: SummonerService,
    private val matchService: MatchService) :
    ViewModel() {

    lateinit var summoner: SummonerDto

    fun getSumbySumName(summonerName: String) = summonerService.getSumbySumName(summonerName)

    fun getMatchesByAccount(accountId: String) = matchService.getMatchesByAccount(accountId)

    fun getMatch(matchId: Long) = matchService.getMatch(matchId.toString())

}
