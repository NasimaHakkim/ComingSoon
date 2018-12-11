package comingsoon.nasima.com.android.ui.explore

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cognitiveclouds.ds.services.model.DataWrapper
import comingsoon.nasima.com.android.R
import comingsoon.nasima.com.android.services.model.explore.popular.ExplorePopularResponse
import comingsoon.nasima.com.android.viewmodel.DiscoverViewModel
import comingsoon.nasima.com.android.adapters.CardStackAdapter
import comingsoon.nasima.com.android.services.model.explore.popular.ResultsItem
import comingsoon.nasima.com.android.services.model.explore.trending.ExploreTrendingResponse
import kotlinx.android.synthetic.main.fragment_discover.*
import androidx.recyclerview.widget.LinearLayoutManager
import comingsoon.nasima.com.android.adapters.TrendingAdapter
import androidx.recyclerview.widget.RecyclerView
import comingsoon.nasima.com.android.ui.MovieDetailActivity
import comingsoon.nasima.com.android.utility.utility.hide
import comingsoon.nasima.com.android.utility.utility.show


/**
 * Created by Nasima Hakkim on 20/11/18.
 */

class DiscoverFragment : Fragment() {

    private lateinit var mDiscoverViewModel: DiscoverViewModel

    companion object {

        private var mPopularList : ArrayList<ResultsItem> = ArrayList()
        private var mTrendingList : ArrayList<comingsoon.nasima.com.android.services.model.explore.trending.ResultsItem> = ArrayList()
        private var mDramaList : ArrayList<comingsoon.nasima.com.android.services.model.explore.trending.ResultsItem> = ArrayList()
        private var mBollywoodList : ArrayList<comingsoon.nasima.com.android.services.model.explore.trending.ResultsItem> = ArrayList()
        private var mTamilList : ArrayList<comingsoon.nasima.com.android.services.model.explore.trending.ResultsItem> = ArrayList()

    }

    private lateinit var mPopularAdapter: CardStackAdapter
    private lateinit var mTrendingAdapter: TrendingAdapter
    private lateinit var mDramaAdapter: TrendingAdapter
    private lateinit var mBollywoodAdapter: TrendingAdapter
    private lateinit var mTamildapter: TrendingAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initRecyclerView()
        initView()
    }

    private fun initRecyclerView() {


        mPopularAdapter = CardStackAdapter(mPopularList , object : CardStackAdapter.OnItemClickListener{ override fun onItemClick(position: Int, view: View) { openMovieDetail(mPopularList[position]) }})
        mTrendingAdapter = TrendingAdapter(mTrendingList , object : TrendingAdapter.OnItemClickListener{ override fun onItemClick(position: Int, view: View) { openMovieDetail(mTrendingList[position]) } },"TRENDING")
        mDramaAdapter = TrendingAdapter(mDramaList , object : TrendingAdapter.OnItemClickListener{ override fun onItemClick(position: Int, view: View) { openMovieDetail(mDramaList[position]) } },"DRAMA")
        mBollywoodAdapter = TrendingAdapter(mBollywoodList , object : TrendingAdapter.OnItemClickListener{ override fun onItemClick(position: Int, view: View) { openMovieDetail(mBollywoodList[position]) } },"BOLLYWOOD")
        mTamildapter = TrendingAdapter(mTamilList , object : TrendingAdapter.OnItemClickListener{ override fun onItemClick(position: Int, view: View) { openMovieDetail(mTamilList[position]) } },"TAMIL")

       /* val setting = RewindAnimationSetting.Builder().setDirection(Direction.Right).setDuration(200).setInterpolator(DecelerateInterpolator()).build()

        val manager = CardStackLayoutManager(context)

        manager.setRewindAnimationSetting(setting)

        manager.setStackFrom(StackFrom.Right)

        manager.setVisibleCount(3)

        manager.setMaxDegree(20f)

        manager.setSwipeThreshold(0.3f)*//*

        cardStackView.layoutManager = manager

        cardStackView.adapter = mPopularAdapter

        cardStackView.swipe()
*/

        cardStackView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        cardStackView.adapter = mPopularAdapter
        cardStackView.isNestedScrollingEnabled = false
        cardStackView.isHorizontalScrollBarEnabled = false
        cardStackView.isVerticalScrollBarEnabled = false
       // cardStackView.itemAnimator = SlideInRightAnimator(OvershootInterpolator(1f))

        setScroll()

        trendingList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        trendingList.adapter = mTrendingAdapter

        popularDramaList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        popularDramaList.adapter = mDramaAdapter

        popularBollywoodList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        popularBollywoodList.adapter = mBollywoodAdapter

        popularTamilList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        popularTamilList.adapter = mTamildapter

    }

    private fun openMovieDetail(movie: comingsoon.nasima.com.android.services.model.explore.trending.ResultsItem) {

        val options = ActivityOptions.makeSceneTransitionAnimation(activity)
        startActivity(Intent(context,MovieDetailActivity::class.java)
            .putExtra("title",movie.title)
            .putExtra("id",movie.id)
            .putExtra("image",movie.posterPath)
            .putExtra("rating",movie.voteAverage)
            .putExtra("overview",movie.overview),options.toBundle())
    }

    private fun openMovieDetail(movie: ResultsItem) {

        val options = ActivityOptions.makeSceneTransitionAnimation(activity)
        startActivity(Intent(context,MovieDetailActivity::class.java)
            .putExtra("title",movie.title)
            .putExtra("id",movie.id)
            .putExtra("image",movie.posterPath)
            .putExtra("rating",movie.voteAverage)
            .putExtra("overview",movie.overview),options.toBundle())
    }

    private fun setScroll() {

        val layoutManager = LinearLayoutManager(context)
        cardStackView.addOnScrollListener( object  : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstItemVisible = (layoutManager).findFirstVisibleItemPosition()
                if (firstItemVisible != 0 && firstItemVisible % mPopularList.size == 0) {
                    recyclerView.layoutManager?.scrollToPosition(0)
                }

            }
        })
    }

    private fun autoScroll() {

        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                cardStackView?.scrollBy(1080, 0)
                handler.postDelayed(this, 4000)
            }
        }
        handler.postDelayed(runnable, 0)
    }

    private fun initViewModel() {
        mDiscoverViewModel = ViewModelProviders.of(this).get(DiscoverViewModel::class.java)
    }

    private fun initView() {

        progressBar.show()

        mDiscoverViewModel.getPopularResponse().observe(this, Observer<DataWrapper<ExplorePopularResponse>> {

            if(it.data != null){
                mPopularList.clear()
                it.data!!.results?.let { it1 -> mPopularList.addAll(it1) }
                mPopularAdapter.notifyDataSetChanged()
                autoScroll()
            }else  progressBar.hide()
        })

        mDiscoverViewModel.getTrendingResponse().observe(this, Observer<DataWrapper<ExploreTrendingResponse>> {

            if(it.data != null){
                mTrendingList.clear()
                it.data!!.results?.let { it1 -> mTrendingList.addAll(it1) }
                mTrendingAdapter.notifyDataSetChanged()
            }else  progressBar.hide()
        })

        mDiscoverViewModel.getDramaResponse().observe(this, Observer<DataWrapper<ExploreTrendingResponse>> {

            if(it.data != null){
                mDramaList.clear()
                it.data!!.results?.let { it1 -> mDramaList.addAll(it1) }
                mDramaAdapter.notifyDataSetChanged()
            }else  progressBar.hide()
        })

        mDiscoverViewModel.getBollywoodResponse().observe(this, Observer<DataWrapper<ExploreTrendingResponse>> {

            if(it.data != null){
                mBollywoodList.clear()
                it.data!!.results?.let { it1 -> mBollywoodList.addAll(it1) }
                mBollywoodAdapter.notifyDataSetChanged()
            }else  progressBar.hide()
        })

        mDiscoverViewModel.getTamilResponse().observe(this, Observer<DataWrapper<ExploreTrendingResponse>> {

            if(it.data != null){
                mTamilList.clear()
                it.data!!.results?.let { it1 -> mTamilList.addAll(it1) }
                mTamildapter.notifyDataSetChanged()
                progressBar.hide()
            }else  progressBar.hide()
        })


    }

}