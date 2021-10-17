package com.sunu.app.apptv.domain.model

data class Acontent(
    val contents: List<Content>,
    val description: List<Description>,
    val type: String // extras
)