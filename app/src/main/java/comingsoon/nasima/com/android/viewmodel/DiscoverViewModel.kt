package comingsoon.nasima.com.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cognitiveclouds.ds.services.model.DataWrapper
import comingsoon.nasima.com.android.base.ComingSoonApplication
import comingsoon.nasima.com.android.services.model.cast.CastResponse
import comingsoon.nasima.com.android.services.model.explore.actors.ActorResponse
import comingsoon.nasima.com.android.services.model.explore.popular.ExplorePopularResponse
import comingsoon.nasima.com.android.services.model.explore.trending.ExploreTrendingResponse

/**
 * Created by Nasima Hakkim on 26/11/18.
 */
class DiscoverViewModel : ViewModel(){

    private lateinit var explorePopularResponse: MutableLiveData<DataWrapper<ExplorePopularResponse>>
    private lateinit var exploreTrendingResponse: MutableLiveData<DataWrapper<ExploreTrendingResponse>>
    private lateinit var exploreDramaResponse: MutableLiveData<DataWrapper<ExploreTrendingResponse>>
    private lateinit var exploreBollywoodResponse: MutableLiveData<DataWrapper<ExploreTrendingResponse>>
    private lateinit var exploreTamilResponse: MutableLiveData<DataWrapper<ExploreTrendingResponse>>
    private lateinit var similarMovieResponse: MutableLiveData<DataWrapper<ExploreTrendingResponse>>
    private lateinit var castResponse: MutableLiveData<DataWrapper<CastResponse>>
    private lateinit var showsResponse: MutableLiveData<DataWrapper<ExploreTrendingResponse>>
    private lateinit var actorsResponse: MutableLiveData<DataWrapper<ActorResponse>>

    fun getPopularResponse(): MutableLiveData<DataWrapper<ExplorePopularResponse>> {

        if (!::explorePopularResponse.isInitialized) {
            explorePopularResponse = MutableLiveData()
        }
        explorePopularResponse = ComingSoonApplication.getComingSoonRepository().popularMovies()
        return explorePopularResponse
    }

    fun getTrendingResponse(): MutableLiveData<DataWrapper<ExploreTrendingResponse>> {

        if (!::exploreTrendingResponse.isInitialized) {
            exploreTrendingResponse = MutableLiveData()
        }
        exploreTrendingResponse = ComingSoonApplication.getComingSoonRepository().trendingMovies()
        return exploreTrendingResponse
    }

    fun getDramaResponse(): MutableLiveData<DataWrapper<ExploreTrendingResponse>> {

        if (!::exploreDramaResponse.isInitialized) {
            exploreDramaResponse = MutableLiveData()
        }
        exploreDramaResponse = ComingSoonApplication.getComingSoonRepository().popularDrama()
        return exploreDramaResponse
    }


    fun getBollywoodResponse(): MutableLiveData<DataWrapper<ExploreTrendingResponse>> {

        if (!::exploreBollywoodResponse.isInitialized) {
            exploreBollywoodResponse = MutableLiveData()
        }
        exploreBollywoodResponse = ComingSoonApplication.getComingSoonRepository().popularBollywood()
        return exploreBollywoodResponse
    }

    fun getTamilResponse(): MutableLiveData<DataWrapper<ExploreTrendingResponse>> {

        if (!::exploreTamilResponse.isInitialized) {
            exploreTamilResponse = MutableLiveData()
        }
        exploreTamilResponse = ComingSoonApplication.getComingSoonRepository().popularTamil()
        return exploreTamilResponse
    }

    fun getSmilarMovies(movieId : Int): MutableLiveData<DataWrapper<ExploreTrendingResponse>> {

        if (!::similarMovieResponse.isInitialized) {
            similarMovieResponse = MutableLiveData()
        }
        similarMovieResponse = ComingSoonApplication.getComingSoonRepository().similarMovies(movieId)
        return similarMovieResponse
    }

    fun getTvShows(): MutableLiveData<DataWrapper<ExploreTrendingResponse>> {

        if (!::showsResponse.isInitialized) {
            showsResponse = MutableLiveData()
        }
        showsResponse = ComingSoonApplication.getComingSoonRepository().tvShows()
        return showsResponse
    }


    fun getActorResponse(): MutableLiveData<DataWrapper<ActorResponse>> {

        if (!::actorsResponse.isInitialized) {
            actorsResponse = MutableLiveData()
        }
        actorsResponse = ComingSoonApplication.getComingSoonRepository().popularActors()
        return actorsResponse
    }

    fun getCastList(movieId : Int): MutableLiveData<DataWrapper<CastResponse>> {

        if (!::castResponse.isInitialized) {
            castResponse = MutableLiveData()
        }
        castResponse = ComingSoonApplication.getComingSoonRepository().castMovie(movieId)
        return castResponse
    }

}