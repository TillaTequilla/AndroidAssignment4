package com.androidAssignment4.ui.mainActivity.adapters

import com.androidAssignment4.model.Contact

interface ContactClickListener {
    fun onDeleteClick(contact: Contact)
    fun onContactClick(contact: Contact)
}