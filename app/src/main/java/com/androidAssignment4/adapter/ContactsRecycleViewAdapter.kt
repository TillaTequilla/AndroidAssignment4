package com.androidAssignment4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemDetailsLookup.ItemDetails
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.androidAssignment4.ui.Contact
import com.androidAssignment4.R
import com.androidAssignment4.databinding.RecycleviewContactItemBinding
import com.androidAssignment4.util.DiffUtil


interface ContactController {
    fun deleteUser(contact: Contact)
    fun showContact(contact: Contact)

}

class ContactsRecycleViewAdapter(private val contactController: ContactController) :
    ListAdapter<Contact, ContactsRecycleViewAdapter.Holder>(DiffUtil) {

    lateinit var selectionTracker: SelectionTracker<Contact>


    inner class Holder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = RecycleviewContactItemBinding.bind(item)
        fun bind(contact: Contact, selectionTracker: SelectionTracker<Contact>) = with(binding) {

            tvContactName.text = contact.name
            tvContactCareer.text = contact.career
            if (contact.imageURL == "null") {
                ivContactPhoto.setImageResource(R.drawable.icon_default_photo)
            } else {
                Glide.with(ivContactPhoto).load(contact.imageURL).circleCrop().into(ivContactPhoto)
            }
            IvRemoveContact.setOnClickListener {
                contactController.deleteUser(contact)
            }
            itemView.setOnClickListener {
                contactController.showContact(contact)
            }

            bindSelectedState(itemView, selectionTracker.isSelected(contact))
            if (selectionTracker.isSelected(contact)) {
                ivContactBorder.setImageResource(R.drawable.selected_user_border)
            } else {
                ivContactBorder.setImageResource(R.drawable.user_border)
            }


        }

        private fun bindSelectedState(view: View, selected: Boolean) {
            view.isActivated = selected
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Contact> =
            object : ItemDetailsLookup.ItemDetails<Contact>() {
                override fun getPosition(): Int = bindingAdapterPosition
                override fun getSelectionKey(): Contact? =
                    (bindingAdapter as ContactsRecycleViewAdapter).currentList[bindingAdapterPosition]
            }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_contact_item, parent, false)
        return Holder(itemView)
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(currentList[position], selectionTracker)
    }


    override fun getItemCount(): Int {
        return currentList.size
    }


}
