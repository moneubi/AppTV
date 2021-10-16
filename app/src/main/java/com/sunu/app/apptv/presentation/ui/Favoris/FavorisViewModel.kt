package com.sunu.app.apptv.presentation.ui.Favoris

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavorisViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Favoris Fragment"
    }
    val text: LiveData<String> = _text
}