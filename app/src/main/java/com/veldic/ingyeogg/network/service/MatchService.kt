package com.veldic.ingyeogg.network.service

import com.veldic.ingyeogg.network.service.retrofit.MatchRetrofitService

class MatchService (private val matchRetrofitService: MatchRetrofitService) {

    fun getMatchesByAccount(accountId: String) = matchRetrofitService.getMatchesByAccount(accountId = accountId)

    fun getMatch(matchId: String) = matchRetrofitService.getMatch(matchId = matchId)
}