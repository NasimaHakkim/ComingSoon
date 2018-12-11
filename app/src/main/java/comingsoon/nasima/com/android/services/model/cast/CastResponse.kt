package comingsoon.nasima.com.android.services.model.cast

import com.google.gson.annotations.SerializedName

data class CastResponse(

	@field:SerializedName("cast")
	val cast: List<CastItem?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("crew")
	val crew: List<CrewItem?>? = null
)