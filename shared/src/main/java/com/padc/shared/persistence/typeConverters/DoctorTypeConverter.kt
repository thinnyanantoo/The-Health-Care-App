package com.padc.shared.persistence.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.shared.data.vos.DoctorVO


class DoctorTypeConverter {
    @TypeConverter
    fun toString(dataList :DoctorVO?): String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(JsonStr: String) : DoctorVO?{
        val dataListType = object  : TypeToken<DoctorVO?>(){}.type
        return Gson().fromJson(JsonStr,dataListType)
    }
}