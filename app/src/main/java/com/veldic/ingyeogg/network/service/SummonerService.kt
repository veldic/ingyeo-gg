package com.veldic.ingyeogg.network.service

import com.veldic.ingyeogg.network.service.retrofit.SummonerRetrofitService

class SummonerService(private val summonerRetrofitService: SummonerRetrofitService) {

    fun getSumbySumName(summonerName: String) =
        summonerRetrofitService.getSumbySumName(summonerName = summonerName)

}
