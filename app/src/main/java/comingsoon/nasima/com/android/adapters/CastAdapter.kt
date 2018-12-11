package comingsoon.nasima.com.android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import comingsoon.nasima.com.android.R
import comingsoon.nasima.com.android.services.model.cast.CastItem
import comingsoon.nasima.com.android.utility.utility.setImage
import comingsoon.nasima.com.android.utility.utility.setImageCircle

/**
 * Created by Nasima Hakkim on 08/12/18.
 */


class CastAdapter (private var mCastList: ArrayList<CastItem?>, private val listener: OnItemClickListener) : RecyclerView.Adapter<CastAdapter.RecyclerItemViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.setData(mCastList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        return RecyclerItemViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return mCastList.size
    }

    inner class RecyclerItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {

        private var ivBackground: ImageView? = null

        init {
            ivBackground = itemView.findViewById(R.id.posterIv)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            view?.let { listener.onItemClick(adapterPosition, it) }
        }

        fun setData(data: CastItem?) {

            data?.profilePath?.let { ivBackground?.setImageCircle(it) }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int , view : View)
    }
}

