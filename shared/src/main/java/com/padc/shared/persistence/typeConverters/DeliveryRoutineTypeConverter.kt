package com.padc.shared.persistence.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.shared.data.vos.DeliveryRoutineVO

class DeliveryRoutineTypeConverter {
    @TypeConverter
    fun toString(dataList : DeliveryRoutineVO): String{
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(JsonStr: String) : DeliveryRoutineVO {
        val dataListType = object  : TypeToken<DeliveryRoutineVO>(){}.type
        return Gson().fromJson(JsonStr,dataListType)
    }
}