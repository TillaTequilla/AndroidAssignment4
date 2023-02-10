package com.androidAssignment4.util

import androidx.recyclerview.selection.ItemKeyProvider
import com.androidAssignment4.adapter.ContactsRecycleViewAdapter

class KeyProvider(private val adapter: ContactsRecycleViewAdapter) :
    ItemKeyProvider<Long>(SCOPE_CACHED) {
    override fun getKey(position: Int): Long? = adapter.currentList[position].id.toLong()
    override fun getPosition(key: Long): Int =
        adapter.currentList.indexOfFirst { it.id.toLong() == key }
}