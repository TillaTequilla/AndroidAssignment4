package com.androidAssignment4.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.androidAssignment4.ui.fragments.ContactsFragment
import com.androidAssignment4.ui.fragments.ProfileFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter((fragment)) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProfileFragment()
            else -> ContactsFragment()
        }
    }
}