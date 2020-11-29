package com.padc.shared.persistence.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.shared.data.vos.DoctorVO

class ChatMessageTypeConverter {
    @TypeConverter
    fun toString(dataList : ArrayList<String>): String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(JsonStr: String) : ArrayList<String> {
        val dataListType = object  : TypeToken<ArrayList<String>>(){}.type
        return Gson().fromJson(JsonStr,dataListType)
    }
}