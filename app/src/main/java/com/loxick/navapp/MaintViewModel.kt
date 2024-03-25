package com.loxick.navapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch

class MaintViewModel(database: MainDB): ViewModel() {
    val database = database
    val itemsList  = database.itemDao().getAllItems()
    fun insertItem(name:String, password:String) = viewModelScope.launch {
        val newUser = Users(name = name, password = password)
        database.itemDao().InsertItem(newUser)
    }

    fun deleteAllUsers(){
        database.itemDao().getAllItems()
    }

    companion object{
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return MaintViewModel(database) as T
            }
        }
    }
}