package com.sunu.app.apptv.presentation.ui.home

import com.google.common.truth.Truth
import org.junit.Test

import org.junit.Before

class HomeViewModelTest{
    private lateinit var homeViewmodel: HomeViewModel

    @Before
    fun setup(){
        homeViewmodel = HomeViewModel()
    }

    @Test
    fun `get movies with empty title`() {
        val values = homeViewmodel.getMoviesTest("")

        Truth.assertThat(values).isFalse()
    }

}