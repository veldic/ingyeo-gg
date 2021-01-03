package com.veldic.ingyeogg.ui.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.veldic.ingyeogg.models.MatchDto
import com.veldic.ingyeogg.models.SummonerDto
import kotlinx.android.synthetic.main.item_match_lose.view.*

class LoseMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val championText : TextView = view.champion_text

    fun render(match: MatchDto, summoner: SummonerDto) {

        var summonerPartId: Int = -1
        match.participantIdentities.forEach {
            if (it.player.accountId == summoner.accountId) {
                summonerPartId = it.participantId
            }
        }
        if (summonerPartId == -1) throw IllegalStateException("There is no summoner in this match")

        match.participants.forEach {
            if (it.participantId == summonerPartId) {
                championText.text = "champId = " + it.championId.toString()
            }
        }
    }
}