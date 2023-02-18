package com.androidAssignment4.ui.mainActivity.fragments

import android.os.Bundle
import android.view.View

import androidx.viewpager2.widget.ViewPager2
import com.androidAssignment4.R
import com.androidAssignment4.architecture.BaseFragment
import com.androidAssignment4.databinding.FragmentProfileBinding


class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        binding.run {
            btnContacts.setOnClickListener {
                viewPager?.currentItem = 1
            }
        }


    }
}