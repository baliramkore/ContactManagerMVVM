package com.rbk.contactmanagermvvm.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDAO {

    @Insert
    suspend fun insertContact(contact: Contact):Long

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
   suspend fun deleteContact(contact: Contact)

   @Query("DELETE FROM contacts_table")
   suspend fun deleteAll()

   @Query("SELECT * FROM contacts_table")
   fun getAllContactsFromDB():LiveData<List<Contact>>
    /* here using LiveData underlying data will be updated as per changes inside database  */
}