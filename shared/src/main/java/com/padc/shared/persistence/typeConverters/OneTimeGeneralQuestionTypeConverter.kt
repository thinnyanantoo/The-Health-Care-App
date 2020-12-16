package com.padc.shared.persistence.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.shared.data.vos.MedicineVO
import com.padc.shared.data.vos.OnetimeGeneralQuestionVO

class OneTimeGeneralQuestionTypeConverter {
    @TypeConverter
    fun toString(dataList : OnetimeGeneralQuestionVO): String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(JsonStr: String) : OnetimeGeneralQuestionVO{
        val dataListType = object  : TypeToken<OnetimeGeneralQuestionVO>(){}.type
        return Gson().fromJson(JsonStr,dataListType)
    }
}