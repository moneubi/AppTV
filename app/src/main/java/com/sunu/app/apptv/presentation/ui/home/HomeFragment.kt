package com.sunu.app.apptv.presentation.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.sunu.app.apptv.R
import com.sunu.app.apptv.adapters.MoviesSearchAdapter
import com.sunu.app.apptv.common.Constatnts
import com.sunu.app.apptv.domain.model.MovieLists
import com.sunu.app.apptv.utils.RecycleViewHelper
import kotlin.math.roundToInt

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var rResult: RecyclerView
    private lateinit var txtInfo: TextView
    private lateinit var inputLayout: TextInputLayout
    private lateinit var inputSearch: TextInputEditText
    private lateinit var moviesSearchAdapter: MoviesSearchAdapter
    private lateinit var progress: LinearProgressIndicator
    private var searchValue: String = ""

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        /*=====================================================================*/
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        txtInfo = root.findViewById(R.id.txt_show_info)
        rResult= root.findViewById(R.id.r_result)
        inputLayout = root.findViewById(R.id.input_layout_search)
        inputSearch = root.findViewById(R.id.input_search)
        progress = root.findViewById(R.id.progress)
        /*=====================================================================*/
        initrecycleView()
        initHomeViewModel()
        //searchMovie("Game")
        /*====================================================================*/
        inputLayout.editText?.doOnTextChanged { inputText, _, _, _ ->
            if (!inputText.isNullOrEmpty()){
                searchValue = inputText.toString()
                inputLayout.setEndIconActivated(true)
            }else{
                inputLayout.setEndIconActivated(false)
                progress.visibility = View.GONE
                rResult.visibility = View.GONE
                txtInfo.text = context.let { it -> it?.resources?.getText(R.string.txt_value_empty) }
                txtInfo.visibility = View.VISIBLE
            }
        }
        inputLayout.setEndIconOnClickListener {
            if (searchValue.isNotEmpty()) {
                hideKeyboard()
                rResult.visibility = View.GONE
                txtInfo.visibility = View.GONE
                progress.visibility = View.VISIBLE
                searchMovie(searchValue)
            }else{
                progress.visibility = View.GONE
                rResult.visibility = View.GONE
                txtInfo.text = context.let { it -> it?.resources?.getText(R.string.txt_value_empty) }
                txtInfo.visibility = View.VISIBLE
            }
        }
        /*====================================================================*/
        return root
    }

    /**
     * Hide keyboard
     * */
    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }
    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun searchMovie(title: String){
        homeViewModel.getMovies("${Constatnts.REF_TITLE}${Constatnts.REF_EGALE}$title")
    }

    /**
     * init HomeViewmodel
     * */
    @SuppressLint("FragmentLiveDataObserve")
    fun initHomeViewModel(){
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.getMovieListObserverable().observe(this, Observer<MovieLists> {
            if (it == null){
                progress.visibility = View.GONE
                txtInfo.text = context.let { it -> it?.resources?.getText(R.string.txt_error_when_search) }
                txtInfo.visibility = View.VISIBLE
            }else{
                if (!it.contents.isNullOrEmpty()) {
                    moviesSearchAdapter.movieList = it.contents.toMutableList()
                    //Notify data changed & show recycleView
                    moviesSearchAdapter.notifyDataSetChanged()
                    progress.visibility = View.GONE
                    rResult.visibility = View.VISIBLE
                }else{
                    progress.visibility = View.GONE
                    txtInfo.text = context.let { it -> it?.resources?.getText(R.string.txt_nothing) }
                    txtInfo.visibility = View.VISIBLE
                }
            }
        })
    }

    /**
     * Method to init the recycleView
     * **/
    private fun initrecycleView(){
        rResult.apply {
            val mLayoutManager: RecyclerView.LayoutManager = StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL)
            this.setHasFixedSize(false)
            this.layoutManager = mLayoutManager
            this.addItemDecoration(RecycleViewHelper(2, dpToPx(10), true))
            this.itemAnimator = DefaultItemAnimator()
            moviesSearchAdapter = MoviesSearchAdapter(context)
            this.adapter = moviesSearchAdapter
        }
    }

    /**
     * Converting dp to pixel
     */
    private fun dpToPx(dp: Int): Int {
        val r = resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            r.displayMetrics
        ).roundToInt()
    }
}