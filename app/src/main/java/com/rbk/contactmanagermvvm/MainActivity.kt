package com.rbk.contactmanagermvvm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rbk.contactmanagermvvm.databinding.ActivityMainBinding
import com.rbk.contactmanagermvvm.repository.ContactRepository
import com.rbk.contactmanagermvvm.room.Contact
import com.rbk.contactmanagermvvm.room.ContactDatabase
import com.rbk.contactmanagermvvm.view.RecyclerAdapter
import com.rbk.contactmanagermvvm.viewmodel.ContactViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var contactViewModel:ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        //Room Database
        val dao=ContactDatabase.getInstance(applicationContext).contactDAO
        val repository=ContactRepository(dao)

        //ViewModel
        contactViewModel=ViewModelProvider(this).get(ContactViewModel::class.java)
        binding.contactViewModel=contactViewModel
        //this: LiveData and Binding Integration
        binding.lifecycleOwner=this

        initRecyclerView()
    }

    private fun initRecyclerView() {

        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        displayUsersList()
    }

    private fun displayUsersList() {
        contactViewModel.contacts.observe((this, Observer {

            binding.recyclerView.adapter=RecyclerAdapter(
                it,{selectedItem:Contact->listItemClicked(selectedItem)}
                )
        }))
    }

    fun listItemClicked(selectedItem:Contact){

        Toast.makeText(this,"Selected Item is${selectedItem.name}",Toast.LENGTH_LONG).show()
    }
}