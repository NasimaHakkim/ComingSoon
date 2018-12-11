package comingsoon.nasima.com.android.ui


import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cognitiveclouds.ds.services.model.DataWrapper
import comingsoon.nasima.com.android.R
import comingsoon.nasima.com.android.adapters.CastAdapter
import comingsoon.nasima.com.android.adapters.TrendingAdapter
import comingsoon.nasima.com.android.services.model.cast.CastItem
import comingsoon.nasima.com.android.services.model.cast.CastResponse
import comingsoon.nasima.com.android.services.model.explore.trending.ExploreTrendingResponse
import comingsoon.nasima.com.android.services.model.explore.trending.ResultsItem
import comingsoon.nasima.com.android.utility.utility.hide
import comingsoon.nasima.com.android.utility.utility.setImage
import comingsoon.nasima.com.android.utility.utility.show
import comingsoon.nasima.com.android.viewmodel.DiscoverViewModel
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Created by Nasima Hakkim on 08/12/18.
 */

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var mDiscoverViewModel: DiscoverViewModel

    private lateinit var mSmilarMoviesAdapter: TrendingAdapter
    private lateinit var mCastAdapter: CastAdapter

    private var mPopularList : ArrayList<ResultsItem> = ArrayList()
    private var mCastList : ArrayList<CastItem?> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWindow()
        setContentView(R.layout.activity_detail)
        initViewModel()
        initListeners()
        initView()
    }

    private fun initListeners() {

        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initViewModel() {
        mDiscoverViewModel = ViewModelProviders.of(this).get(DiscoverViewModel::class.java)
    }


    private fun initView() {

        tvMovieName.text = intent.getStringExtra("title")
        ratingBar.rating = intent.getDoubleExtra("rating",0.0).toFloat()
        posterIv.setImage(intent.getStringExtra("image"))
        tvOverView.text = intent.getStringExtra("overview")

        mSmilarMoviesAdapter = TrendingAdapter(mPopularList , object : TrendingAdapter.OnItemClickListener{ override fun onItemClick(position: Int, view: View) { openMovieDetail(mPopularList[position]) } },"TRENDING")
        similarMovieList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        similarMovieList.adapter = mSmilarMoviesAdapter

        mCastAdapter = CastAdapter(mCastList , object : CastAdapter.OnItemClickListener{ override fun onItemClick(position: Int, view: View) { } })
        castList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        castList.adapter = mCastAdapter

        progressBar.show()
        mDiscoverViewModel.getSmilarMovies(intent.getIntExtra("id",0)).observe(this, Observer<DataWrapper<ExploreTrendingResponse>> {

            if(it.data != null){
                it.data!!.results?.let { it1 -> mPopularList.addAll(it1) }
                mSmilarMoviesAdapter.notifyDataSetChanged()
            }else progressBar.hide()
        })

        mDiscoverViewModel.getCastList(intent.getIntExtra("id",0)).observe(this, Observer<DataWrapper<CastResponse>> {

            if(it.data != null){
                it.data!!.cast?.let { it1 -> mCastList.addAll(it1) }
                mSmilarMoviesAdapter.notifyDataSetChanged()
                progressBar.hide()
            }else  progressBar.hide()
        })
    }

    private fun openMovieDetail(movie: ResultsItem) {

        val options = ActivityOptions.makeSceneTransitionAnimation(this)
        startActivity(
            Intent(this,MovieDetailActivity::class.java)
                .putExtra("id",movie.id)
                .putExtra("title",movie.title)
                .putExtra("image",movie.posterPath)
                .putExtra("rating",movie.voteAverage)
                .putExtra("overview",movie.overview),options.toBundle())
    }

    private fun initWindow() {

        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            enterTransition = Explode()
            exitTransition = Explode()
        }
    }


}