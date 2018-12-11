package comingsoon.nasima.com.android.base

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Nasima Hakkim on 20/11/18.
 */

abstract class BaseActivity : AppCompatActivity(){

    abstract fun getLayoutID() : Int

    abstract fun initializeUI()

    abstract fun hideStatusBar(): Boolean

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        if (hideStatusBar()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            this.window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        setContentView(getLayoutID())
        initializeUI()
    }
}