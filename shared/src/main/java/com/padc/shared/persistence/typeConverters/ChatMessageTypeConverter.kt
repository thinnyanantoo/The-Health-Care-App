package com.padc.shared.persistence.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.shared.data.vos.ChatMessageVO

class ChatMessageTypeConverter {
    @TypeConverter
    fun toString(dataList : ChatMessageVO): String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(JsonStr: String) : ChatMessageVO {
        val dataListType = object  : TypeToken<ChatMessageVO>(){}.type
        return Gson().fromJson(JsonStr,dataListType)
    }
}