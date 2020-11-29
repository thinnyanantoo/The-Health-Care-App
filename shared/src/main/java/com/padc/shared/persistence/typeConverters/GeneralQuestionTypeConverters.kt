package com.padc.shared.persistence.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.shared.data.vos.GeneralQuestionVO

class GeneralQuestionTypeConverters {
    @TypeConverter
    fun toString(dataList : GeneralQuestionVO): String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(JsonStr: String) : GeneralQuestionVO{
        val dataListType = object  : TypeToken<GeneralQuestionVO>(){}.type
        return Gson().fromJson(JsonStr,dataListType)
    }
}