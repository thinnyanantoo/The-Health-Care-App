package com.padc.shared.persistence.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecentlyDoctorTypeConverter {
    @TypeConverter
    fun toString(dataList : ArrayList<RecentlyDoctorTypeConverter>): String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(JsonStr: String) : ArrayList<RecentlyDoctorTypeConverter> {
        val dataListType = object  : TypeToken<ArrayList<RecentlyDoctorTypeConverter>>(){}.type
        return Gson().fromJson(JsonStr,dataListType)
    }
}