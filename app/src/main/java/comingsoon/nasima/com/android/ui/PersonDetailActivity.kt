package comingsoon.nasima.com.android.ui


import android.graphics.BlurMaskFilter
import android.os.Bundle
import android.transition.Explode
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import comingsoon.nasima.com.android.R
import comingsoon.nasima.com.android.utility.utility.setImage
import kotlinx.android.synthetic.main.activity_person_detail.*

/**
 * Created by Nasima Hakkim on 09/12/18.
 */


class PersonDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWindow()
        setContentView(R.layout.activity_person_detail)
        initView()
    }

    private fun initView() {

        tvMovieName.text = intent.getStringExtra("title")
        ratingBar.rating = intent.getDoubleExtra("rating",0.0).toFloat()
        backgroundIv.setImage(intent.getStringExtra("image"))
        backgroundBlurIv.setImage(intent.getStringExtra("image"))
        tvOverView.text = intent.getStringExtra("overview")

    }

    private fun initWindow() {

        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            enterTransition = Explode()
            exitTransition = Explode()
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }


}