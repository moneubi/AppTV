package com.sunu.app.apptv.presentation.ui.favoris

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sunu.app.apptv.R

class FavorisFragment : Fragment() {

    private lateinit var favorisViewModel: FavorisViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        favorisViewModel =
                ViewModelProvider(this).get(FavorisViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favoris, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        favorisViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}