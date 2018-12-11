package comingsoon.nasima.com.android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import comingsoon.nasima.com.android.R
import comingsoon.nasima.com.android.services.model.explore.trending.ResultsItem
import comingsoon.nasima.com.android.utility.utility.hide
import comingsoon.nasima.com.android.utility.utility.setImage

/**
 * Created by Nasima Hakkim on 26/11/18.
 */

class TrendingAdapter (private var mPopularList: ArrayList<ResultsItem>, private val listener: OnItemClickListener , private val type : String) : RecyclerView.Adapter<TrendingAdapter.RecyclerItemViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.setData(mPopularList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_trending, parent, false)

       /* when(type){
            "DRAMA"     -> view = LayoutInflater.from(parent.context).inflate(R.layout.item_trending, parent, false)
            "TRENDING"  -> view = LayoutInflater.from(parent.context).inflate(R.layout.item_trending, parent, false)
            "BOLLYWOOD" -> view = LayoutInflater.from(parent.context).inflate(R.layout.item_trending, parent, false)
            "TAMIL"     -> view = LayoutInflater.from(parent.context).inflate(R.layout.item_trending, parent, false)
        }*/

        return RecyclerItemViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return mPopularList.size
    }

    inner class RecyclerItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {

        private var cardName: TextView? = null
        private var ivBackground: ImageView? = null

        init {
            cardName = itemView.findViewById(R.id.posterNameTv)
            ivBackground = itemView.findViewById(R.id.posterIv)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            view?.let { listener.onItemClick(adapterPosition, it) }
        }

        fun setData(data: ResultsItem) {

            cardName?.text = data.title

            data.posterPath?.let { ivBackground?.setImage(it) }
            cardName?.hide()

            /*when(type){

                "DRAMA"    -> {
                    data.posterPath?.let { ivBackground?.setImage(it) }
                    cardName?.show()
                }

                "TRENDING" -> {
                    data.posterPath?.let { ivBackground?.setImage(it) }
                    cardName?.hide()
                }

                "BOLLYWOOD" -> {
                    data.posterPath?.let { ivBackground?.setImage(it) }
                    cardName?.show()
                }

                "TAMIL" -> {
                    data.posterPath?.let { ivBackground?.setImage(it) }
                    cardName?.show()
                }
            }*/
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int , view : View)
    }
}

