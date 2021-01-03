package com.veldic.ingyeogg.models

data class MatchlistDto(
    val matches: List<MatchReferenceDto>,
    val startIndex: Int,
    val endIndex: Int,
    val totalGames: Int
)

data class MatchReferenceDto(
    val gameId: Long,
    val role: String,
    val season: Int,
    val platformId: String,
    val champion: Int,
    val queue: Int,
    val lane: String,
    val timestamp: Long
)
