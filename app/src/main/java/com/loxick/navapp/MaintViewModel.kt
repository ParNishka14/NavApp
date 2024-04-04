package com.loxick.navapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import navigationBar.currentUser

class MaintViewModel(database: MainDB): ViewModel() {
    val database = database
    val itemsList  = database.itemDao().getAllItems()
    fun insertItem(name:String, password:String) = viewModelScope.launch {
        val newUser = Users(name = name, password = password, liked = arrayListOf())
        database.itemDao().InsertItem(newUser)
    }

     suspend fun addFavoriteToList(){
        database.itemDao().UpdateItem(users = currentUser)
    }

    fun deleteAllUsers(){
        database.itemDao().deleteAllUsers()
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