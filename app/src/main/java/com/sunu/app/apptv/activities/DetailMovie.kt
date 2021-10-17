package com.sunu.app.apptv.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sunu.app.apptv.R
import com.sunu.app.apptv.common.Shared
import com.sunu.app.apptv.presentation.ui.detail.DetailMoviefragment

class DetailMovie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        //inject fragment detail movie
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailMoviefragment.newInstance())
                .commit()
        }
    }
}