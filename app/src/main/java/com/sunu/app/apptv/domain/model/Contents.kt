package com.sunu.app.apptv.domain.model

data class ContentList(val contents: Contents)

data class Contents(
        val acontents: List<Acontent>,
        val availability: List<Availability>,
        val bannerinfo: List<Bannerinfo>,
        val description: List<List<String>>,
        val duration: String, // PT1H48M26S
        val fullscreenimageurl: String, // /data_plateforme/program/39866/origin_gotlastwatcw0152010_685tl.jpg?size=large
        val highlefticon: List<String>,
        val highrighticon: Any, // null
        val id: String, // GOTLASTWATCW0152010
        val imageurl: String, // /data_plateforme/program/39866/origin_gotlastwatcw0152010_685tl.jpg?size=medium
        val isbookmarkable: Boolean, // true
        val isdownloadable: Boolean, // true
        val linearplanning: Any, // null
        val number: Any, // null
        val parentalrating: Int, // 3
        val pitch: String, // A chaque saison, «Game of Thrones» a fait tomber de nouveaux records de visionnage. Pour conclure les aventures des Stark, Lannister et Targaryen, HBO a concocté une saison de six épisodes pour un budget colossal de 90 millions de dollars. La cinéaste britannique Jeanie Finlay a suivi pendant une année entière les différentes équipes à l'oeuvre sur cette série. Elle dévoile les coulisses du tournage et de la production de cette ultime saison qui s'annonce épique. Pour ces adieux à la série, acteurs et techniciens ont dû faire face à des conditions climatiques parfois extrêmes, à des échéances très serrées, sans parler de l'attente des fans, à l'affût du moindre scoop.
        val playinfo: Playinfo,
        val playinfoid: Playinfoid,
        val subtitle: Any, // null
        val subtitlefocus: Any, // null
        val summary: String, // A chaque saison, «Game of Thrones» a fait tomber de nouveaux records de visionnage. Pour conclure les aventures des Stark, Lannister et Targaryen, HBO a concocté une saison de six épisodes pour un budget colossal de 90 millions de dollars. La cinéaste britannique Jeanie Finlay a suivi pendant une année entière les différentes équipes à l'oeuvre sur cette série. Elle dévoile les coulisses du tournage et de la production de cette ultime saison qui s'annonce épique. Pour ces adieux à la série, acteurs et techniciens ont dû faire face à des conditions climatiques parfois extrêmes, à des échéances très serrées, sans parler de l'attente des fans, à l'affût du moindre scoop.
        val title: List<Titles>,
        val zonesinfo: Zonesinfo
)

