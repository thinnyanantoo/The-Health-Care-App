package com.padc.shared.persistence.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.shared.data.vos.MedicineVO

class MedicineTypeConverter{
    @TypeConverter
    fun toString(dataList : ArrayList<MedicineVO>): String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(JsonStr: String) : ArrayList<MedicineVO> {
        val dataListType = object  : TypeToken<ArrayList<MedicineVO>>(){}.type
        return Gson().fromJson(JsonStr,dataListType)
    }
}