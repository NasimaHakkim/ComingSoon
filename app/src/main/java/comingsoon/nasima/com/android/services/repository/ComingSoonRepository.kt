package comingsoon.nasima.com.android.services.repository

import androidx.lifecycle.MutableLiveData
import com.cognitiveclouds.ds.services.model.DataWrapper
import com.cognitiveclouds.ds.services.model.ErrorResponse
import comingsoon.nasima.com.android.services.model.cast.CastResponse
import comingsoon.nasima.com.android.services.model.explore.actors.ActorResponse
import comingsoon.nasima.com.android.services.model.explore.popular.ExplorePopularResponse
import comingsoon.nasima.com.android.services.model.explore.trending.ExploreTrendingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComingSoonRepository(private val remoteApiEndPoint: RemoteApiEndPoint) {

    fun popularMovies() : MutableLiveData<DataWrapper<ExplorePopularResponse>> {

        val popularMovies = MutableLiveData<DataWrapper<ExplorePopularResponse>>()

        remoteApiEndPoint.discoverPopularMovies().enqueue(object : Callback<ExplorePopularResponse> {
            override fun onFailure(call: Call<ExplorePopularResponse>, t: Throwable) {
                popularMovies.value = DataWrapper(null, ErrorResponse(true, "error", "error"))
            }

            override fun onResponse(call: Call<ExplorePopularResponse>, response: Response<ExplorePopularResponse>) {
                if(response.isSuccessful) popularMovies.value = DataWrapper(response.body(),null)
                else popularMovies.value = DataWrapper(null,ErrorResponse(true,response.errorBody().toString(),response.message()))
            }

        })

        return popularMovies

    }


    fun trendingMovies() : MutableLiveData<DataWrapper<ExploreTrendingResponse>> {

        val popularMovies = MutableLiveData<DataWrapper<ExploreTrendingResponse>>()

        remoteApiEndPoint.discoverTrendingMovies().enqueue(object : Callback<ExploreTrendingResponse> {
            override fun onFailure(call: Call<ExploreTrendingResponse>, t: Throwable) {
                popularMovies.value = DataWrapper(null, ErrorResponse(true, "error", "error"))
            }

            override fun onResponse(call: Call<ExploreTrendingResponse>, response: Response<ExploreTrendingResponse>) {
                if(response.isSuccessful) popularMovies.value = DataWrapper(response.body(),null)
                else popularMovies.value = DataWrapper(null,ErrorResponse(true,response.errorBody().toString(),response.message()))
            }

        })

        return popularMovies

    }



    fun popularDrama() : MutableLiveData<DataWrapper<ExploreTrendingResponse>> {

        val popularMovies = MutableLiveData<DataWrapper<ExploreTrendingResponse>>()

        remoteApiEndPoint.discoverPopularDrama().enqueue(object : Callback<ExploreTrendingResponse> {
            override fun onFailure(call: Call<ExploreTrendingResponse>, t: Throwable) {
                popularMovies.value = DataWrapper(null, ErrorResponse(true, "error", "error"))
            }

            override fun onResponse(call: Call<ExploreTrendingResponse>, response: Response<ExploreTrendingResponse>) {
                if(response.isSuccessful) popularMovies.value = DataWrapper(response.body(),null)
                else popularMovies.value = DataWrapper(null,ErrorResponse(true,response.errorBody().toString(),response.message()))
            }

        })

        return popularMovies

    }

    fun popularBollywood() : MutableLiveData<DataWrapper<ExploreTrendingResponse>> {

        val popularMovies = MutableLiveData<DataWrapper<ExploreTrendingResponse>>()

        remoteApiEndPoint.discoverPopularBollywood().enqueue(object : Callback<ExploreTrendingResponse> {
            override fun onFailure(call: Call<ExploreTrendingResponse>, t: Throwable) {
                popularMovies.value = DataWrapper(null, ErrorResponse(true, "error", "error"))
            }

            override fun onResponse(call: Call<ExploreTrendingResponse>, response: Response<ExploreTrendingResponse>) {
                if(response.isSuccessful) popularMovies.value = DataWrapper(response.body(),null)
                else popularMovies.value = DataWrapper(null,ErrorResponse(true,response.errorBody().toString(),response.message()))
            }

        })

        return popularMovies

    }


    fun popularTamil() : MutableLiveData<DataWrapper<ExploreTrendingResponse>> {

        val popularMovies = MutableLiveData<DataWrapper<ExploreTrendingResponse>>()

        remoteApiEndPoint.discoverPopularTamil().enqueue(object : Callback<ExploreTrendingResponse> {
            override fun onFailure(call: Call<ExploreTrendingResponse>, t: Throwable) {
                popularMovies.value = DataWrapper(null, ErrorResponse(true, "error", "error"))
            }

            override fun onResponse(call: Call<ExploreTrendingResponse>, response: Response<ExploreTrendingResponse>) {
                if(response.isSuccessful) popularMovies.value = DataWrapper(response.body(),null)
                else popularMovies.value = DataWrapper(null,ErrorResponse(true,response.errorBody().toString(),response.message()))
            }

        })

        return popularMovies

    }

    fun similarMovies(movieId : Int) : MutableLiveData<DataWrapper<ExploreTrendingResponse>> {

        val popularMovies = MutableLiveData<DataWrapper<ExploreTrendingResponse>>()

        remoteApiEndPoint.similarMovies(movieId).enqueue(object : Callback<ExploreTrendingResponse> {
            override fun onFailure(call: Call<ExploreTrendingResponse>, t: Throwable) {
                popularMovies.value = DataWrapper(null, ErrorResponse(true, "error", "error"))
            }

            override fun onResponse(call: Call<ExploreTrendingResponse>, response: Response<ExploreTrendingResponse>) {
                if(response.isSuccessful) popularMovies.value = DataWrapper(response.body(),null)
                else popularMovies.value = DataWrapper(null,ErrorResponse(true,response.errorBody().toString(),response.message()))
            }

        })

        return popularMovies

    }


    fun tvShows() : MutableLiveData<DataWrapper<ExploreTrendingResponse>> {

        val popularMovies = MutableLiveData<DataWrapper<ExploreTrendingResponse>>()

        remoteApiEndPoint.getTvShows().enqueue(object : Callback<ExploreTrendingResponse> {
            override fun onFailure(call: Call<ExploreTrendingResponse>, t: Throwable) {
                popularMovies.value = DataWrapper(null, ErrorResponse(true, "error", "error"))
            }

            override fun onResponse(call: Call<ExploreTrendingResponse>, response: Response<ExploreTrendingResponse>) {
                if(response.isSuccessful) popularMovies.value = DataWrapper(response.body(),null)
                else popularMovies.value = DataWrapper(null,ErrorResponse(true,response.errorBody().toString(),response.message()))
            }

        })

        return popularMovies

    }

    fun popularActors() : MutableLiveData<DataWrapper<ActorResponse>> {

        val popularMovies = MutableLiveData<DataWrapper<ActorResponse>>()

        remoteApiEndPoint.getPopularActress().enqueue(object : Callback<ActorResponse> {
            override fun onFailure(call: Call<ActorResponse>, t: Throwable) {
                popularMovies.value = DataWrapper(null, ErrorResponse(true, "error", "error"))
            }

            override fun onResponse(call: Call<ActorResponse>, response: Response<ActorResponse>) {
                if(response.isSuccessful) popularMovies.value = DataWrapper(response.body(),null)
                else popularMovies.value = DataWrapper(null,ErrorResponse(true,response.errorBody().toString(),response.message()))
            }

        })

        return popularMovies

    }


    fun castMovie(movieId : Int) : MutableLiveData<DataWrapper<CastResponse>> {

        val popularMovies = MutableLiveData<DataWrapper<CastResponse>>()

        remoteApiEndPoint.getCast(movieId).enqueue(object : Callback<CastResponse> {
            override fun onFailure(call: Call<CastResponse>, t: Throwable) {
                popularMovies.value = DataWrapper(null, ErrorResponse(true, "error", "error"))
            }

            override fun onResponse(call: Call<CastResponse>, response: Response<CastResponse>) {
                if(response.isSuccessful) popularMovies.value = DataWrapper(response.body(),null)
                else popularMovies.value = DataWrapper(null,ErrorResponse(true,response.errorBody().toString(),response.message()))
            }

        })

        return popularMovies

    }



}