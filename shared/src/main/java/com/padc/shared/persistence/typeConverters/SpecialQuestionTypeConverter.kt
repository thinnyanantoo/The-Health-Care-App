package com.padc.shared.persistence.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.shared.data.vos.SpecialQuestionVO

class SpecialQuestionTypeConverter {
    @TypeConverter
    fun toString(dataList : ArrayList<SpecialQuestionVO>): String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(JsonStr: String) : ArrayList<SpecialQuestionVO>{
        val dataListType = object  : TypeToken<ArrayList<SpecialQuestionVO>>(){}.type
        return Gson().fromJson(JsonStr,dataListType)
    }
}