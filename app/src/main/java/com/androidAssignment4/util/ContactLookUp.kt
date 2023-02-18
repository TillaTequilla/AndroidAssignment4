package com.androidAssignment4.util

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.androidAssignment4.adapter.ContactsRecycleViewAdapter
import com.androidAssignment4.model.Contact

class ContactLookUp(private val recyclerView: RecyclerView) : ItemDetailsLookup<Contact>() {

    override fun getItemDetails(e: MotionEvent): ItemDetails<Contact>? {
        val view = recyclerView.findChildViewUnder(e.x, e.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as ContactsRecycleViewAdapter.Holder).getItemDetails()
        }
        return null
    }

}