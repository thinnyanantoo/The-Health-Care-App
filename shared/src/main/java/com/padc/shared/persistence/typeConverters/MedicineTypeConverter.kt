package com.padc.shared.persistence.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.shared.data.vos.MedicineVO

class MedicineTypeConverter{
    @TypeConverter
    fun toString(dataList : MedicineVO): String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(JsonStr: String) : MedicineVO {
        val dataListType = object  : TypeToken<MedicineVO>(){}.type
        return Gson().fromJson(JsonStr,dataListType)
    }
}