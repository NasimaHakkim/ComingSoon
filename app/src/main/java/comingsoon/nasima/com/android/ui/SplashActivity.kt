package comingsoon.nasima.com.android.ui

import android.content.Intent
import comingsoon.nasima.com.android.R
import comingsoon.nasima.com.android.base.BaseActivity
import java.util.*

/**
 * Created by Nasima Hakkim on 20/11/18.
 */

class SplashActivity : BaseActivity() {

    private var timer: Timer? = Timer()

    override fun initializeUI() {
        scheduleTimer()
    }

    private fun scheduleTimer() {

        timer?.schedule(object : TimerTask() {
            override fun run() {
                launchApp()
            }
        }, 2000)

    }

    private fun launchApp() {
        startActivity(Intent(this, HomeActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        finish()
    }

    override fun onResume() {
        super.onResume()
        scheduleTimer()
    }

    override fun hideStatusBar(): Boolean {
        return true
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_splash
    }


}