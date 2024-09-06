package com.rbk.contactmanagermvvm.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbk.contactmanagermvvm.repository.ContactRepository
import com.rbk.contactmanagermvvm.room.Contact
import kotlinx.coroutines.launch

//stores and manage UI-related Data
// separating the UI-related logic from UI Controller(Activity/Fragment)
//extending viewmodel and
class ContactViewModel(private val repository: ContactRepository) :ViewModel(), Observable {


    val contacts=repository.contacts
    private var isUpdateOrDelete=false
    private lateinit var contacToUpdateOrDelete:Contact

    @Bindable
    val inputName =MutableLiveData<String?>()
    @Bindable
    val inputEmail=MutableLiveData<String?>()
    @Bindable
    val inputMobile=MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateBtnText=MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteBtnText=MutableLiveData<String>()


    init {
        saveOrUpdateBtnText.value="Save"
        clearAllOrDeleteBtnText.value="Delete All"
    }


    fun insert(contact: Contact)=viewModelScope.launch {
        repository.insert(contact)
    }

    fun delete(contact: Contact)=viewModelScope.launch {

        repository.deleteContact(contact)
        inputName.value=null
        inputEmail.value=null
        inputMobile.value=null
        isUpdateOrDelete=false
        saveOrUpdateBtnText.value="Save"
        clearAllOrDeleteBtnText.value="Clear All"

    }
    fun update(contact: Contact)=viewModelScope.launch {
        repository.updateContact(contact)
        inputName.value=null
        inputEmail.value=null
        inputMobile.value=null
        isUpdateOrDelete=false
        saveOrUpdateBtnText.value="Save"
        clearAllOrDeleteBtnText.value="Clear All"
    }


    fun clearAll()=viewModelScope.launch {

        repository.deleteAll()
    }

    fun saveOrUpdate(){

        if (isUpdateOrDelete){
            contacToUpdateOrDelete.name=inputName.value!!
            contacToUpdateOrDelete.email=inputEmail.value!!
            contacToUpdateOrDelete.mobile=inputMobile.value!!
        }else{
         val name = inputName.value.toString()
         val email=inputEmail.value.toString()
         val mobile=inputMobile.value.toString()

            insert(Contact(0,name,email,mobile))


            inputName.value=null
            inputEmail.value=null
            inputMobile.value=null

        }
    }

    fun clearAllOrDelete(){
        if (isUpdateOrDelete){
            delete(contacToUpdateOrDelete)

        }else{
            clearAll()
        }
    }

    fun initUpdateAndDelete(contact: Contact){

        inputName.value=contact.name
        inputEmail.value=contact.email
        inputMobile.value=contact.mobile
        isUpdateOrDelete=true
        contacToUpdateOrDelete=contact
        saveOrUpdateBtnText.value="save"
       clearAllOrDeleteBtnText.value="Delete"
    }




    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {


    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}