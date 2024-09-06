package com.rbk.contactmanagermvvm.repository

import com.rbk.contactmanagermvvm.room.Contact
import com.rbk.contactmanagermvvm.room.ContactDAO

// acts as bridge between viewmodel and datasource
class ContactRepository(private val contactDAO: ContactDAO) {
    val contacts=contactDAO.getAllContactsFromDB()

    suspend fun insert(contact: Contact): Long {
        return contactDAO.insertContact(contact)
    }

    suspend fun deleteContact(contact: Contact){

        return contactDAO.deleteContact(contact)
    }
    suspend fun updateContact(contact: Contact){

        return contactDAO.updateContact(contact)
    }
    suspend fun deleteAll(){
        return contactDAO.deleteAll()
    }
}