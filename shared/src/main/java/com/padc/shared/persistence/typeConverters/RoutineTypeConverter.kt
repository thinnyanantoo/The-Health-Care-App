package com.padc.shared.persistence.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.shared.data.vos.RoutineVO

class RoutineTypeConverter{
    @TypeConverter
    fun toString(dataList : RoutineVO): String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(JsonStr: String) : RoutineVO {
        val dataListType = object  : TypeToken<RoutineVO>(){}.type
        return Gson().fromJson(JsonStr,dataListType)
    }
}