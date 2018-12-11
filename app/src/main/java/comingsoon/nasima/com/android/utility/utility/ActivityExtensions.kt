package comingsoon.nasima.com.android.utility.utility

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.WindowManager
import androidx.core.content.ContextCompat

/**
 * Created by Nasima Hakkim on 20/11/18.
 */

fun Context.color(id: Int): Int = ContextCompat.getColor(this, id)

@SuppressLint("InlinedApi")
fun Activity.setStatusBarColor(colorResId: Int) {
    supportsLollipop {
        with(window) {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            statusBarColor = ContextCompat.getColor(context, colorResId)
        }
    }
}


inline fun supportsLollipop(func: () -> Unit) =
    supportsVersion(Build.VERSION_CODES.LOLLIPOP, func)


inline fun supportsVersion(ver: Int, func: () -> Unit) {
    if (Build.VERSION.SDK_INT >= ver) {
        func.invoke()
    }
}
