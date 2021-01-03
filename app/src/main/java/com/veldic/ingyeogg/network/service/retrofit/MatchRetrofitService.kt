package com.veldic.ingyeogg.network.service.retrofit

import com.veldic.ingyeogg.models.MatchDto
import com.veldic.ingyeogg.models.MatchlistDto
import com.veldic.ingyeogg.models.SummonerDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchRetrofitService {
    // Get matches by account
    @GET("/lol/match/v4/matchlists/by-account/{encryptedAccountId}")
    fun getMatchesByAccount(
        @Path("encryptedAccountId") accountId: String
    ): Single<MatchlistDto>

    // Get a match info by match Id
    @GET("/lol/match/v4/matches/{matchId}")
    fun getMatch(
        @Path("matchId") matchId: String
    ): Single<MatchDto>
}