package com.androidAssignment4.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidAssignment4.util.UsersList

class ContactsViewModel : ViewModel() {

    private val _contactList: MutableLiveData<List<Contact>> = MutableLiveData()

    val contactList: LiveData<List<Contact>> = _contactList

    init {
        _contactList.value = UsersList.getUsers()
    }

    fun getListUsers() = _contactList.value

    fun deleteContact(index: Int) {
        _contactList.value = _contactList.value?.toMutableList()?.apply {
            removeAt(index)
        }
    }

    fun deleteContact(contact: Contact) {
        _contactList.value = _contactList.value?.minus(contact)
    }

    fun addContact(contact: Contact) {
        _contactList.value = _contactList.value?.plus(contact)
    }

    fun deleteContact(list: List<Contact>) {
        _contactList.value = _contactList.value?.minus(list.toSet())
    }
}