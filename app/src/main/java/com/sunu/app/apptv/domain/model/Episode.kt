package com.sunu.app.apptv.domain.model

data class Episode(
    val acontents: List<Acontent>,
    val availability: List<Availability>,
    val bannerinfo: List<Bannerinfo>,
    val description: List<List<Any>>,
    val duration: String, // PT59M6S
    val fullscreenimageurl: String, // /data_plateforme/saison/733/origin_letronede01w0056709_yvh5x.jpg?size=large
    val highlefticon: List<String>,
    val highrighticon: Any, // null
    val id: String, // LETRONE0101W0056710
    val imageurl: String, // /data_plateforme/saison/733/origin_letronede01w0056709_yvh5x.jpg?size=medium
    val isbookmarkable: Boolean, // true
    val isdownloadable: Boolean, // true
    val linearplanning: Any, // null
    val menutitle: List<Menutitle>,
    val menutitlefocus: List<Menutitlefocu>,
    val number: Int, // 1
    val parentalrating: Int, // 3
    val pitch: String, // A Winterfell, Eddard Stark reçoit la visite du roi Robert Baratheon. Celui-ci vient lui demander d'être son conseiller.
    val playinfo: Playinfo,
    val playinfoid: Playinfoid,
    val subtitle: Any, // null
    val subtitlefocus: Any, // null
    val summary: String, // Dans le nord de Westeros, des patrouilleurs de la Garde de Nuit font une mauvaise rencontre dans les bois qui jouxtent le grand Mur. De plus, le cadavre d'un loup-garou retrouvé aux abords du château de la famille Stark ne présage rien de bon, d'autant que l'hiver approche. Chez les Stark de Winterfell, Eddard et sa famille reçoivent d'ailleurs le roi Robert Baratheon. Après la mort de son conseiller, le souverain vient demander à Eddard de rejoindre Port-Réal, la capitale des Sept Royaumes, pour occuper ses fonctions. Sur un autre continent, Viserys Targaryen ne rêve que de revenir à Westeros pour récupérer le Trône de Fer. Il compte pour cela utiliser sa soeur Daenerys et lever une armée. Une adaptation soignée du «Trône de Fer», la saga du romancier George R. R. Martin.
    val title: List<Titles>,
    val zonesinfo: Zonesinfo
)