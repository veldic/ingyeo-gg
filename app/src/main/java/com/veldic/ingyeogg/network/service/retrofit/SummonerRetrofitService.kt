package com.veldic.ingyeogg.network.service.retrofit

import com.veldic.ingyeogg.models.SummonerDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SummonerRetrofitService {
    // Get a summoner by summoner name
    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}")
    fun getSumbySumName(
        @Path("summonerName") summonerName: String
    ): Single<SummonerDto>
}
