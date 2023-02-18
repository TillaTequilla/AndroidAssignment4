package com.androidAssignment4.ui.authActivity

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.androidAssignment4.util.Constance
import com.androidAssignment4.util.PreferenceHelper

class AuthFragmentViewModel : ViewModel() {
    lateinit var sharedPreferences: SharedPreferences

    fun putData(toString: String, toString1: String, checked: Boolean) {
        PreferenceHelper.putValueToSharedPreferences(
            Constance.SHARED_PREFERENCES_EMAIL,
            toString
        )
        PreferenceHelper.putValueToSharedPreferences(
            Constance.SHARED_PREFERENCES_PASSWORD,
            toString1
        )
        PreferenceHelper.putValueToSharedPreferences(
            Constance.SHARED_PREFERENCES_REMEMBER,
            checked
        )
    }

}