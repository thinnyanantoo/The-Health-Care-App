package com.padc.shared.persistence.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.shared.data.vos.CaseSummaryVO

class CaseSummaryTypeConverter {
    @TypeConverter
    fun toString(dataList : CaseSummaryVO): String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(JsonStr: String) : CaseSummaryVO {
        val dataListType = object  : TypeToken<CaseSummaryVO>(){}.type
        return Gson().fromJson(JsonStr,dataListType)
    }
}