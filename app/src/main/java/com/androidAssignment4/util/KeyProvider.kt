package com.androidAssignment4.util

import androidx.recyclerview.selection.ItemKeyProvider
import com.androidAssignment4.adapter.ContactsRecycleViewAdapter
import com.androidAssignment4.ui.Contact

class KeyProvider(private val adapter: ContactsRecycleViewAdapter) :
    ItemKeyProvider<Contact>(SCOPE_CACHED) {
    override fun getKey(position: Int): Contact? = adapter.currentList[position]
    override fun getPosition(key: Contact): Int =
        adapter.currentList.indexOfFirst { it == key }
}