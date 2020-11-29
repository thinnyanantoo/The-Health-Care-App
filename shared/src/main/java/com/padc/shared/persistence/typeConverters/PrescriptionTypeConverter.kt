package com.padc.shared.persistence.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.shared.data.vos.GeneralQuestionVO
import com.padc.shared.data.vos.PresriptionVO

class PrescriptionTypeConverter {
    @TypeConverter
    fun toString(dataList : PresriptionVO): String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(JsonStr: String) : PresriptionVO {
        val dataListType = object  : TypeToken<PresriptionVO>(){}.type
        return Gson().fromJson(JsonStr,dataListType)
    }
}