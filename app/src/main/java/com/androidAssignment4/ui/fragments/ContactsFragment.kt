package com.androidAssignment4.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidAssignment4.R
import com.androidAssignment4.adapter.ContactController
import com.androidAssignment4.adapter.ContactsRecycleViewAdapter
import com.androidAssignment4.architecture.BaseFragment
import com.androidAssignment4.util.Constance.ADD_CONTACT_RESULT_KEY
import com.androidAssignment4.ui.Contact
import com.androidAssignment4.ui.ContactsViewModel

import com.androidAssignment4.util.Constance
import com.androidAssignment4.util.SwipeToDeleteCallback
import com.androidAssignment4.databinding.FragmentContactsBinding
import com.androidAssignment4.util.ContactLookUp
import com.androidAssignment4.util.KeyProvider
import com.google.android.material.snackbar.Snackbar


class ContactsFragment : BaseFragment<FragmentContactsBinding>(FragmentContactsBinding::inflate),
    ContactController {

    private val contactViewModel: ContactsViewModel by activityViewModels()

    private val adapter: ContactsRecycleViewAdapter by lazy {
        ContactsRecycleViewAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentListener()
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter


        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                deleteUser(viewHolder.absoluteAdapterPosition)
            }
        }


        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)


        binding.tvAddContact.setOnClickListener {
            val dialog = DialogFragmentAddContact()
            dialog.show(parentFragmentManager, "addContact")
        }

        binding.ivContactBack.setOnClickListener {
            findNavController().popBackStack()
        }

        contactViewModel.contactList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        val tracker = SelectionTracker.Builder<Long>(
            "selection-1",
            binding.recyclerView,
            KeyProvider(adapter),
            ContactLookUp(binding.recyclerView),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(SelectionPredicates.createSelectAnything()).build()

        adapter.selectionTracker = tracker


        binding.ivMultiselectTrash.setOnClickListener {
            println(tracker.selection)
            println(tracker.selection)
            println(tracker.selection)
            println(tracker.selection)
            println(tracker.selection)
        }
    }

    override fun showTrash() {
        binding.ivMultiselectTrash.isVisible = true
    }

    override fun hideTrash() {
        binding.ivMultiselectTrash.isVisible = false
    }

    private fun setFragmentListener() {
        setFragmentResultListener(ADD_CONTACT_RESULT_KEY) { _, bundle ->
            val result = bundle.getSerializable(Constance.CONTACT_SERIALIZABLE)
            addContact(result as Contact)
        }
    }

    private fun addContact(contact: Contact) {
        contactViewModel.addContact(contact)
    }

    fun deleteUser(index: Int) {
        val contact = contactViewModel.contactList.value?.get(index)!!
        contactViewModel.deleteContact(index)
        undoUserDeletion(binding.root, contact)
    }

    override fun deleteUser(contact: Contact) {
        contactViewModel.deleteContact(contact)
        undoUserDeletion(binding.root, contact)
    }

    override fun showContact(contact: Contact) {
        val dialog = DialogFragmentShowContact()
        val args = Bundle()
        args.putSerializable(Constance.CONTACT_SERIALIZABLE, contact)
        dialog.arguments = args
        dialog.show(parentFragmentManager, "showContact")
    }

    private fun undoUserDeletion(view: View, contact: Contact?) {
        val delMessage = Snackbar.make(
            view,
            getString(R.string.contacts_sbRemoved, contact!!.name),
            Snackbar.LENGTH_LONG
        )
        delMessage.setAction("Cancel") {
            contactViewModel.addContact(contact)
        }.show()
    }

}