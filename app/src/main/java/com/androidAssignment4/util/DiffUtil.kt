package com.androidAssignment4.util

import androidx.recyclerview.widget.DiffUtil
import com.androidAssignment4.ui.Contact

object DiffUtil : DiffUtil.ItemCallback<Contact>() {

    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return  oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }
}
