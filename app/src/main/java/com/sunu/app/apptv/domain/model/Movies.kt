package com.sunu.app.apptv.domain.model

data class MovieLists(val contents: List<Movies>)
data class Movies(
    val detaillink: String,
    val duration: String,
    val fullscreenimageurl: String,
    val highlefticon: Any,
    val highrighticon: Any,
    val id: String,
    val imageurl: String,
    val lowrightinfo: Any,
    val playinfoid: PlayInfoId,
    val subtitle: String,
    val subtitlefocus: Any,
    val title: List<Titles>
    )
