package comingsoon.nasima.com.android.services.repository

import comingsoon.nasima.com.android.BuildConfig
import comingsoon.nasima.com.android.services.model.cast.CastResponse
import comingsoon.nasima.com.android.services.model.explore.actors.ActorResponse
import comingsoon.nasima.com.android.services.model.explore.popular.ExplorePopularResponse
import comingsoon.nasima.com.android.services.model.explore.trending.ExploreTrendingResponse
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface RemoteApiEndPoint{

    @GET("discover/movie?api_key=${BuildConfig.API_KEY}&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false")
    fun discoverPopularMovies() : Call<ExplorePopularResponse>

    @GET("trending/all/day?api_key=${BuildConfig.API_KEY}")
    fun discoverTrendingMovies() : Call<ExploreTrendingResponse>

    @GET("discover/movie?api_key=${BuildConfig.API_KEY}&with_genres=18&sort_by=vote_average.desc&vote_count.gte=10")
    fun discoverPopularDrama() : Call<ExploreTrendingResponse>

    @GET("discover/movie?api_key=${BuildConfig.API_KEY}&region=IN&sort_by=popularity.desc&certification_country=IN&include_adult=false&include_video=false&page=1&year=${Calendar.YEAR}&with_original_language=hi")
    fun discoverPopularBollywood() : Call<ExploreTrendingResponse>

    @GET("discover/movie?api_key=${BuildConfig.API_KEY}&region=IN&sort_by=popularity.desc&certification_country=IN&include_adult=false&include_video=false&page=1&year=${Calendar.YEAR}&with_original_language=ta")
    fun discoverPopularTamil() : Call<ExploreTrendingResponse>

    @GET("movie/{id}/similar?api_key=${BuildConfig.API_KEY}&language=en-US&page=1")
    fun similarMovies(@Path("id") movieId : Int) : Call<ExploreTrendingResponse>

    @GET("movie/{id}/credits?api_key=${BuildConfig.API_KEY}")
    fun getCast(@Path("id") movieId : Int) : Call<CastResponse>

    @GET("tv/popular?api_key=${BuildConfig.API_KEY}&language=en-US")
    fun getTvShows() : Call<ExploreTrendingResponse>

    @GET("person/popular?api_key=${BuildConfig.API_KEY}&language=en-US&page=1")
    fun getPopularActress() : Call<ActorResponse>

}