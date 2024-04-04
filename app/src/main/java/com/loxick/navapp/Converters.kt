package com.loxick.navapp

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    @TypeConverter
    fun fromString(value: String?): ArrayList<Recept>{
        val listType = object : TypeToken<ArrayList<Recept>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<Recept?>): String{
       return Gson().toJson(list)
    }
}