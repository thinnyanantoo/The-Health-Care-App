package com.padc.shared.persistence.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.shared.data.vos.SendByVO

class SendByVOTypeConverter {
    @TypeConverter
    fun toString(dataList : SendByVO): String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(JsonStr: String) : SendByVO {
        val dataListType = object  : TypeToken<SendByVO>(){}.type
        return Gson().fromJson(JsonStr,dataListType)
    }
}