package com.veldic.ingyeogg.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veldic.ingyeogg.R
import com.veldic.ingyeogg.models.MatchDto
import com.veldic.ingyeogg.ui.viewholder.LoseMatchViewHolder
import com.veldic.ingyeogg.ui.viewholder.WinMatchViewHolder

class MatchAdapter(private val viewModel: MainViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var matches: List<MatchDto> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = 
        when(viewType) {
            MATCH_WIN_VIEW_TYPE -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_match_win, parent, false)
                    .let { WinMatchViewHolder(it) }
            }
            MATCH_LOSE_VIEW_TYPE -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_match_lose, parent, false)
                    .let { LoseMatchViewHolder(it) }
            }
            else -> throw IllegalStateException("viewType must be 0 or 1")
        }

    

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is WinMatchViewHolder -> {
                holder.render(matches[position], viewModel.summoner)
            }
        }
    }

    override fun getItemViewType(position: Int): Int = when (checkWin(matches[position])) {
        true -> MATCH_WIN_VIEW_TYPE
        false -> MATCH_LOSE_VIEW_TYPE
    }


    fun checkWin(match: MatchDto): Boolean {
        val summoner = viewModel.summoner
        var summonerPartId: Int = -1
        var winTeamId: Int
        match.participantIdentities.forEach {
            if (it.player.accountId == summoner.accountId) {
                summonerPartId = it.participantId
            }
        }
        if (summonerPartId == -1) throw IllegalStateException("There is no summoner in this match")

        winTeamId = if(match.teams[0].win == "Win") match.teams[0].teamId else match.teams[1].teamId

        match.participants.forEach {
            if(it.participantId == summonerPartId) {
                return it.teamId == winTeamId
            }
        }

        throw IllegalStateException("checking win is wrong")
    }

    companion object {
        private const val MATCH_WIN_VIEW_TYPE = 0
        private const val MATCH_LOSE_VIEW_TYPE = 1
    }

}