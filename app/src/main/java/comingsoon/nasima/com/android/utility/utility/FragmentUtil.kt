package comingsoon.nasima.com.android.utility.utility

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


/**
 * Created by Nasima Hakkim on 20/11/18.
 */


fun loadFragment(fm: FragmentManager, container: Int, fragment : Fragment) {
    val transaction = fm.beginTransaction()
    val fragmentName = fragment::class.java.simpleName as String
    transaction.replace(container, fragment, fragmentName)
    transaction.addToBackStack(fragmentName)
    transaction.commit()
}

fun loadFragmentWithoutBackStack(fm: FragmentManager, container: Int, fragment: Fragment) {
    val transaction = fm.beginTransaction()
    val fragmentName = fragment::class.java.simpleName as String
    transaction.disallowAddToBackStack()
    transaction.replace(container, fragment, fragmentName)
    transaction.commit()
}