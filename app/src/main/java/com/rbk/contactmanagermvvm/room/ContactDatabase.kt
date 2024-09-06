package com.rbk.contactmanagermvvm.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase :RoomDatabase()  {
    abstract val contactDAO: ContactDAO


    //Singleton design pattern
    // only one instance of the database exists
    //unnecessary overhead associated with repeated database creation
    //companion object :Define a static singleton instance of this DB Class
    //way to define static members inside class
    companion object{

    @Volatile
    private var INSTANCE: ContactDatabase?=null
   fun getInstance(context:Context): ContactDatabase {
       //prevents multiple access
       synchronized(this){

           var instance= INSTANCE
           if(instance==null){

               instance= Room.databaseBuilder(
                   context.applicationContext,
                   ContactDatabase::class.java,"contacts_db"
                   ).build()
           }
           INSTANCE =instance
           return instance
       }
   }

    }

}