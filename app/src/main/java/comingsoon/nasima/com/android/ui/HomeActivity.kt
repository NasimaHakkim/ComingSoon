package comingsoon.nasima.com.android.ui

import android.view.View
import androidx.fragment.app.Fragment
import comingsoon.nasima.com.android.R
import comingsoon.nasima.com.android.base.BaseActivity
import comingsoon.nasima.com.android.ui.actors.ActorFragment
import comingsoon.nasima.com.android.ui.explore.DiscoverFragment
import comingsoon.nasima.com.android.ui.movies.MoviesFragment
import comingsoon.nasima.com.android.ui.shows.ShowsFragment
import comingsoon.nasima.com.android.utility.utility.*
import kotlinx.android.synthetic.main.activity_home.*
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle

/**
 * Created by Nasima Hakkim on 20/11/18.
 */

class HomeActivity : BaseActivity() {

    override fun initializeUI() {
        initBottomView()
        initSideMenu()
    }

    private fun initBottomView() {

        setTabMenuTypeFace(bottomNavigationView, this)
        loadFragmentByClassName(DiscoverFragment())
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.item_discover -> {
                    item.isCheckable = true
                    loadFragmentByClassName(DiscoverFragment())
                }
                R.id.item_movies -> {
                    item.isCheckable = true
                    loadFragmentByClassName(MoviesFragment())
                }
                R.id.item_shows -> {
                    item.isCheckable = true
                    loadFragmentByClassName(ShowsFragment())
                }
                R.id.item_people -> {
                    item.isCheckable = true
                    loadFragmentByClassName(ActorFragment())

                }
                R.id.item_settings -> {
                    item.isCheckable = true
                    showToast("Under Developement")
                    // loadFragmentByClassName(DiscoverFragment())
                }
            }
            true
        }

        discoverTv.setOnClickListener {
            bottomNavigationView.selectedItemId = R.id.item_discover
            drawer_layout.closeDrawer()
            loadFragmentByClassName(DiscoverFragment())
        }

        moviesTv.setOnClickListener {
            bottomNavigationView.selectedItemId = R.id.item_movies
            drawer_layout.closeDrawer()
            loadFragmentByClassName(MoviesFragment())
        }

        showsTv.setOnClickListener {
            bottomNavigationView.selectedItemId = R.id.item_shows
            drawer_layout.closeDrawer()
            loadFragmentByClassName(ShowsFragment())
        }

        peopleTv.setOnClickListener {
            bottomNavigationView.selectedItemId = R.id.item_people
            drawer_layout.closeDrawer()
            loadFragmentByClassName(ActorFragment())
        }

        settingsTv.setOnClickListener {
            drawer_layout.closeDrawer()
            showToast("Under Developement")
        }

        logoutTv.setOnClickListener {
            drawer_layout.closeDrawer()
            showToast("Under Developement")
        }


        profileTv.setOnClickListener {
            drawer_layout.closeDrawer()
            showToast("Under Developement")
        }

    }

    private lateinit var drawerToggle: DuoDrawerToggle

    private fun initSideMenu() {

        drawerToggle = object : DuoDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                menuShadow.show()
                setStatusBarColor(R.color.blue_800)
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                menuShadow.hide()
                setStatusBarColor(R.color.md_grey_400)

            }
        }

        drawer_layout.setDrawerListener(drawerToggle)
        drawerToggle.syncState()

    }

    private fun loadFragmentByClassName(homeFragment: Fragment) {
        loadFragment(supportFragmentManager, R.id.container, homeFragment)
    }

    override fun hideStatusBar(): Boolean {
        return false
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_home
    }

}