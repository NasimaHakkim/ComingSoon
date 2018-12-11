package comingsoon.nasima.com.android.ui.movies

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.cognitiveclouds.ds.services.model.DataWrapper
import comingsoon.nasima.com.android.R
import comingsoon.nasima.com.android.ui.MovieDetailActivity
import comingsoon.nasima.com.android.utility.utility.hide
import comingsoon.nasima.com.android.utility.utility.show
import comingsoon.nasima.com.android.viewmodel.DiscoverViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import comingsoon.nasima.com.android.adapters.MoviesAdapter
import comingsoon.nasima.com.android.services.model.explore.popular.ExplorePopularResponse
import comingsoon.nasima.com.android.services.model.explore.popular.ResultsItem


/**
 * Created by Nasima Hakkim on 09/12/18.
 */

class MoviesFragment : Fragment() {

    companion object {

        private var mTrendingList : ArrayList<ResultsItem> = ArrayList()


    }

    private lateinit var mTrendingAdapter: MoviesAdapter

    private lateinit var mDiscoverViewModel: DiscoverViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initRecyclerView()
        initView()
    }

    private fun initRecyclerView() {

        val manager = GridLayoutManager(context, 3 , RecyclerView.VERTICAL, false)
        mTrendingAdapter = MoviesAdapter(
            mTrendingList,
            object : MoviesAdapter.OnItemClickListener{ override fun onItemClick(position: Int, view: View) { openMovieDetail(mTrendingList[position]) } })
        moviesList.layoutManager = manager
        moviesList.adapter = mTrendingAdapter
    }

    private fun openMovieDetail(movie: ResultsItem) {

        val options = ActivityOptions.makeSceneTransitionAnimation(activity)
        startActivity(
            Intent(context, MovieDetailActivity::class.java)
                .putExtra("title",movie.title)
                .putExtra("id",movie.id)
                .putExtra("image",movie.posterPath)
                .putExtra("rating",movie.voteAverage)
                .putExtra("overview",movie.overview),options.toBundle())
    }


    private fun initViewModel() {
        mDiscoverViewModel = ViewModelProviders.of(this).get(DiscoverViewModel::class.java)
    }

    private fun initView() {

        progressBar.show()

        mDiscoverViewModel.getPopularResponse().observe(this, Observer<DataWrapper<ExplorePopularResponse>> {

            if (it.data != null) {
                mTrendingList.clear()
                it.data!!.results?.let { it1 -> mTrendingList.addAll(it1) }
                mTrendingAdapter.notifyDataSetChanged()
                progressBar.hide()
            } else progressBar.hide()
        })

    }

}