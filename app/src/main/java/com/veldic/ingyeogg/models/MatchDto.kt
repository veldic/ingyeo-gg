package com.veldic.ingyeogg.models

data class MatchDto(
    val gameId: Long,
    val participantIdentities: List<ParticipantIdentityDto>,
    val queueId: Int,
    val gameType: String,
    val gameDuration: Long,
    val teams: List<TeamStatsDto>,
    val mapId: Int,
    val gameMode: String,
    val participants: List<ParticipantDto>
)

data class ParticipantIdentityDto(
    val participantId: Int,
    val player: PlayerDto
)

data class PlayerDto(
    val profileIcon: Int,
    val accountId: String,
    val summonerName: String,
    val summonerId: String
)

data class TeamStatsDto(
    val teamId: Int,
    val win: String
)

data class ParticipantDto(
    val participantId: Int,
    val championId: Int,
    val teamId: Int,
    val spell1Id: Int,
    val spell2Id: Int
)