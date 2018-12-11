package comingsoon.nasima.com.android.utility.utility

import android.content.Context
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import comingsoon.nasima.com.android.R
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by Nasima Hakkim on 20/11/18.
 */


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.getString(res: Int): String? = resources.getString(res)

fun Context.showToast(message : String ) {
    Toast.makeText( this, message, Toast.LENGTH_SHORT ).show()
}

fun ImageView.setImage(imagePath : String ) {
    val options = RequestOptions()
    Glide.with(this.context).load("https://image.tmdb.org/t/p/original$imagePath").apply(options).into(this)
}

fun ImageView.setImageCircle(imagePath : String ) {

    Glide.with(context)
        .load("https://image.tmdb.org/t/p/original$imagePath")
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}


fun View.toggleVisibility(){
    if (this.isVisible()) this.hide() else this.show()
}

fun View.isVisible() = visibility == View.VISIBLE


fun setTabMenuTypeFace(view: BottomNavigationView, context : Context) {

    val bottomNavTypeface = ResourcesCompat.getFont(context, R.font.lineto_circular_pro_medium)
    val typefaceSpan = bottomNavTypeface?.let { CustomTypefaceSpan("", it) }
    for (i in 0 until view.menu.size()) {
        val menuItem = view.menu.getItem(i)
        val spannableTitle = SpannableStringBuilder(menuItem.title)
        spannableTitle.setSpan(typefaceSpan, 0, spannableTitle.length, 0)
        menuItem.title = spannableTitle
    }

}