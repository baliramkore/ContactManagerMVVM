package com.rbk.contactmanagermvvm.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class Contact(

    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo("contact_name")
    var name:String,
    @ColumnInfo("contact_email")
    var email:String,
    @ColumnInfo("contact_mobile")
    var mobile:String
)
