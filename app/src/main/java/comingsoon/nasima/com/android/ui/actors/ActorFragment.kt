package comingsoon.nasima.com.android.ui.actors

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearSnapHelper
import com.cognitiveclouds.ds.services.model.DataWrapper
import comingsoon.nasima.com.android.R
import comingsoon.nasima.com.android.adapters.ActorsAdapter
import comingsoon.nasima.com.android.scroll.HalfCurveLayoutManager
import comingsoon.nasima.com.android.services.model.explore.actors.ActorResponse
import comingsoon.nasima.com.android.services.model.explore.actors.ResultsItem
import comingsoon.nasima.com.android.ui.MovieDetailActivity
import comingsoon.nasima.com.android.ui.PersonDetailActivity
import comingsoon.nasima.com.android.utility.utility.hide
import comingsoon.nasima.com.android.utility.utility.show
import comingsoon.nasima.com.android.viewmodel.DiscoverViewModel
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * Created by Nasima Hakkim on 09/12/18.
 */


class ActorFragment : Fragment() {

    companion object {
        private var mTrendingList : ArrayList<ResultsItem?> = ArrayList()
    }

    private lateinit var mTrendingAdapter: ActorsAdapter

    private lateinit var mDiscoverViewModel: DiscoverViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_actors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initRecyclerView()
        initView()
    }

    private fun initRecyclerView() {

        mTrendingAdapter = ActorsAdapter(mTrendingList , object : ActorsAdapter.OnItemClickListener{ override fun onItemClick(position: Int, view: View) {
            mTrendingList[position]?.let { openMovieDetail(it) }
        } })
      /*  moviesList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        moviesList.adapter = mTrendingAdapter*/

        moviesList?.setCenterEdgeItems(true)
        val manager = HalfCurveLayoutManager(context, 1.0f)
        moviesList?.layoutManager = manager
        val helper = LinearSnapHelper()
        moviesList.adapter = mTrendingAdapter
        helper.attachToRecyclerView(moviesList)


    }

    private fun openMovieDetail(movie: ResultsItem) {

        val options = ActivityOptions.makeSceneTransitionAnimation(activity)
        startActivity(
            Intent(context, PersonDetailActivity::class.java)
                .putExtra("title",movie.name)
                .putExtra("id",movie.id)
                .putExtra("rating",movie.knownFor?.get(0)?.voteAverage)
                .putExtra("overview",movie.knownFor?.get(0)?.overview)
                .putExtra("image",movie.profilePath),options.toBundle())
    }


    private fun initViewModel() {
        mDiscoverViewModel = ViewModelProviders.of(this).get(DiscoverViewModel::class.java)
    }

    private fun initView() {

        progressBar.show()
        mDiscoverViewModel.getActorResponse().observe(this, Observer<DataWrapper<ActorResponse>> {

            if (it.data != null) {
                mTrendingList.clear()
                it.data!!.results?.let { it1 -> mTrendingList.addAll(it1) }
                moviesList.scrollToPosition(mTrendingList.size/2)
                mTrendingAdapter.notifyDataSetChanged()
                progressBar.hide()
            } else progressBar.hide()
        })


    }

}
