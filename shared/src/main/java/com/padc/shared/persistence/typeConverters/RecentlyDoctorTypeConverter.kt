package com.padc.shared.persistence.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.shared.data.vos.RecentlyDoctorVo

class RecentlyDoctorTypeConverter {
    @TypeConverter
    fun toString(dataList : RecentlyDoctorVo): String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(JsonStr: String) : RecentlyDoctorVo{
        val dataListType = object  : TypeToken<RecentlyDoctorVo>(){}.type
        return Gson().fromJson(JsonStr,dataListType)
    }
}