package br.com.sicredi.techchallenge.features.events.presentation.view.navigation

import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import br.com.sicredi.techchallenge.R
import br.com.sicredi.techchallenge.features.events.presentation.view.constants.ParameterConstants
import br.com.sicredi.techchallenge.features.events.presentation.view.fragments.EventCheckInFragment
import br.com.sicredi.techchallenge.features.events.presentation.view.fragments.EventDetailsFragment
import br.com.sicredi.techchallenge.features.events.presentation.view.fragments.EventsFragment
import java.util.*

object NavigationFlow {

    fun navigateToEvents(fragmentManager: FragmentManager) {
        navigateTo(
            fragmentManager = fragmentManager,
            fragment = EventsFragment()
        )
    }

    fun navigateToEventDetails(fragmentManager: FragmentManager, itemId: String) {
        navigateTo(
            fragmentManager = fragmentManager,
            fragment = EventDetailsFragment(),
            args = Bundle().apply {
                putString(ParameterConstants.EVENT_ID, itemId)
            }
        )
    }

    fun navigateToEventCheckIn(
        fragmentManager: FragmentManager,
        eventId: String,
        eventImageUrl: String
    ) {
        navigateTo(
            fragmentManager = fragmentManager,
            fragment = EventCheckInFragment(),
            args = Bundle().apply {
                putString(ParameterConstants.EVENT_ID, eventId)
                putString(ParameterConstants.EVENT_IMAGE_URL, eventImageUrl)
            }
        )
    }

    fun navigateBackTo(activity: FragmentActivity, fragmentTag: String) {
        val fragmentManager = activity.supportFragmentManager
        while (fragmentManager.backStackEntryCount > 1 && getActiveFragments(fragmentManager)[0]?.tag != fragmentTag) {
            fragmentManager.popBackStackImmediate()
        }
        if (fragmentManager.backStackEntryCount < 1) {
            activity.finish()
        }
    }

    fun navigateBack(activity: FragmentActivity) {
        val fragmentManager = activity.supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            activity.finish()
        }
    }

    private fun navigateTo(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        args: Bundle? = null
    ) {
        fragment.arguments = args
        fragment.enterTransition = Fade()
        fragment.exitTransition = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide(Gravity.START)
        } else Fade()

        fragmentManager.beginTransaction()
            .replace(
                R.id.container,
                fragment,
                fragment.javaClass.name
            )
            .addToBackStack(fragment.javaClass.name)
            .commitAllowingStateLoss()
    }

    fun getActiveFragments(fragmentManager: FragmentManager): ArrayList<Fragment?> {
        val fragments = ArrayList<Fragment?>()
        for (i in 0 until fragmentManager.backStackEntryCount) {
            fragments.add(
                fragmentManager.findFragmentByTag(
                    fragmentManager.getBackStackEntryAt(i).name
                )
            )
        }
        fragments.reverse()
        return fragments
    }
}