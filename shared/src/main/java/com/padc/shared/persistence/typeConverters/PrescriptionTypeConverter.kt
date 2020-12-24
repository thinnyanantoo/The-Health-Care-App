package com.padc.shared.persistence.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.shared.data.vos.PresriptionVO

class PrescriptionTypeConverter {
    @TypeConverter
    fun toString(dataList : ArrayList<PresriptionVO>): String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(JsonStr: String) : ArrayList<PresriptionVO> {
        val dataListType = object  : TypeToken<ArrayList<PresriptionVO>>(){}.type
        return Gson().fromJson(JsonStr,dataListType)
    }
}