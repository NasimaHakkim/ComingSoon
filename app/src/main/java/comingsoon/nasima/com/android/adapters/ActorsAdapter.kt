package comingsoon.nasima.com.android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import comingsoon.nasima.com.android.R
import comingsoon.nasima.com.android.services.model.explore.actors.ResultsItem
import comingsoon.nasima.com.android.utility.utility.setImageCircle

/**
 * Created by Nasima Hakkim on 09/12/18.
 */

class ActorsAdapter (private var mCastList: ArrayList<ResultsItem?>, private val listener: OnItemClickListener) : RecyclerView.Adapter<ActorsAdapter.RecyclerItemViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.setData(mCastList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_actor, parent, false)
        return RecyclerItemViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return mCastList.size
    }

    inner class RecyclerItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {

        private var ivBackground: ImageView? = null
        private var name: TextView? = null

        init {
            ivBackground = itemView.findViewById(R.id.posterIv)
            name = itemView.findViewById(R.id.posterNameTv)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            view?.let { listener.onItemClick(adapterPosition, it) }
        }

        fun setData(data: ResultsItem?) {

            data?.profilePath?.let { ivBackground?.setImageCircle(it) }
            name?.text = data?.name?.substringAfter(" ")
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int , view : View)
    }
}

