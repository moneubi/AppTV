package com.sunu.app.apptv.domain.model

data class Content(
        val detaillink: String, // /apps/v2/details/serie/PSGAMEOFTHRW0058624
        val duration: String, // PT1H15M25S
        val fullscreenimageurl: String, // /data_plateforme/saison/1691/origin_gameofthr08w0149292_ni72q.jpg?size=large
        val highlefticon: Any, // null
        val highrighticon: Any, // null
        val id: String, // GAMEOFT0806W0149298
        val imageurl: String, // /data_plateforme/saison/1691/origin_gameofthr08w0149292_ni72q.jpg?size=small
        val lowrightinfo: Any, // null
        val playinfo: Playinfo,
        val playinfoid: Playinfoid,
        val subtitle: String, // saisons 1 à 8
        val subtitlefocus: Any, // null
        val title: List<Titles>
)