package com.sunu.app.apptv.presentation.ui.detail

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.sunu.app.apptv.R
import com.sunu.app.apptv.common.Constatnts
import com.sunu.app.apptv.domain.model.ContentList
import com.sunu.app.apptv.domain.model.Movies
import kotlinx.android.synthetic.main.detail_movie_fragment.*

class DetailMoviefragment : Fragment() {

    companion object {
        fun newInstance() = DetailMoviefragment()
    }

    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private lateinit var movie: Movies

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        getSaisons()

        Glide.with(requireContext())
            .load("${Constatnts.BASE_URL}${movie.fullscreenimageurl}")
            .placeholder(R.drawable.placeholder)
            .into(img_fulcreen)
        val title = movie.title[0]
        txt_title_movie.text = title.value
        txt_subtitle.text = movie.subtitle
    }

    fun getSaisons(){
        detailMovieViewModel.getContents(movie.id)
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun initViewModel(){
        detailMovieViewModel = ViewModelProvider(this).get(DetailMovieViewModel::class.java)
        // TODO: Use the ViewModel
        detailMovieViewModel.setMovieShared()
        movie = detailMovieViewModel.getMovieShared()

        detailMovieViewModel.getContentListobservable().observe(this, Observer<ContentList?> {
            if (it == null){
                Toast.makeText(context,"Content null", Toast.LENGTH_LONG).show()
            }else{
                if (!it.contents.pitch.isNullOrEmpty()) {
                    txt_pitch.text = it.contents.pitch
                }
            }
        })
    }
}