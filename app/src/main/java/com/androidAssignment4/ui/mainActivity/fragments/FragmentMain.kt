package com.androidAssignment4.ui.mainActivity.fragments

import android.os.Bundle
import android.view.View
import com.androidAssignment4.adapter.ViewPagerAdapter
import com.androidAssignment4.architecture.BaseFragment
import com.androidAssignment4.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator


class FragmentMain : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tb, binding.viewPager) { tab, pos ->
            tab.text =
                when (pos) {
                    0 -> "Profile"
                    else -> "Contacts"
                }

        }.attach()
    }
}