package comingsoon.nasima.com.android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import comingsoon.nasima.com.android.R
import comingsoon.nasima.com.android.services.model.explore.popular.ResultsItem
import comingsoon.nasima.com.android.utility.utility.setImage
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.core.view.ViewCompat
import jp.wasabeef.recyclerview.animators.BaseItemAnimator


/**
 * Created by Nasima Hakkim on 26/11/18.
 */

class CardStackAdapter (private var mPopularList: ArrayList<ResultsItem>, private val listener: OnItemClickListener) : RecyclerView.Adapter<CardStackAdapter.RecyclerItemViewHolder>()  {

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {

        try {
            holder.setData(mPopularList[position])
            ViewCompat.animate(holder.itemView)
                .translationX(holder.itemView.rootView.width.toFloat())
                .setDuration(300)
                .start()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false)
        return RecyclerItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mPopularList.size * 2
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
            data.backdropPath?.let { ivBackground?.setImage(it) }
            cardName?.text = data.title
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int , view : View)
    }
}

