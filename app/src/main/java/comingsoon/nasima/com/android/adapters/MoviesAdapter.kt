package comingsoon.nasima.com.android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import comingsoon.nasima.com.android.R
import comingsoon.nasima.com.android.services.model.explore.popular.ResultsItem
import comingsoon.nasima.com.android.utility.utility.setImage

/**
 * Created by Nasima Hakkim on 09/12/18.
 */

class MoviesAdapter (private var mPopularList: ArrayList<ResultsItem>, private val listener: OnItemClickListener) : RecyclerView.Adapter<MoviesAdapter.RecyclerItemViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.setData(mPopularList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return RecyclerItemViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return mPopularList.size
    }

    inner class RecyclerItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {

        private var ivBackground: ImageView? = null
        private var movieName: TextView? = null
        private var ratingBar: RatingBar? = null

        init {
            ivBackground = itemView.findViewById(R.id.posterIv)
            movieName = itemView.findViewById(R.id.posterNameTv)
            ratingBar = itemView.findViewById(R.id.ratingBar)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            view?.let { listener.onItemClick(adapterPosition, it) }
        }

        fun setData(data: ResultsItem) {
            data.posterPath?.let { ivBackground?.setImage(it) }
            movieName?.text = data.title
            if(data.voteAverage != null)
            ratingBar?.rating = data.voteAverage.toFloat()
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int , view : View)
    }
}

