package com.rbk.contactmanagermvvm.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.recyclerview.widget.RecyclerView
import com.rbk.contactmanagermvvm.R
import com.rbk.contactmanagermvvm.databinding.CardLayoutBinding
import com.rbk.contactmanagermvvm.room.Contact

class RecyclerAdapter(private val contactList: List<Contact>,private var clickListener:(Contact)->Unit):
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {



     class MyViewHolder(val cardBinding:CardLayoutBinding):RecyclerView.ViewHolder(cardBinding.root) {

         fun bind(contact: Contact,clickListener: (Contact) -> Unit){
             cardBinding.nameTxtView.text=contact.name
             cardBinding.emailTxtView.text=contact.email
             cardBinding.mobileTxtView.text=contact.mobile
             cardBinding.listItemLayout.setOnClickListener {
                 clickListener(contact)
             }


         }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layOutInflater = LayoutInflater.from(parent.context)
        val cardItemBinding = DataBindingUtil.inflate<CardLayoutBinding>(
            layOutInflater,
            R.layout.card_layout,
            parent,
            false
        )

        return MyViewHolder(cardItemBinding)
    }

    override fun getItemCount(): Int {

        return contactList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(contactList[position],clickListener)


    }


}