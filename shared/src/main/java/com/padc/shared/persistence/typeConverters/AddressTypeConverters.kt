package com.padc.shared.persistence.typeConverters

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.padc.shared.data.vos.AddressVO

class AddressTypeConverters {
        @TypeConverter
        fun toString(dataList: AddressVO): String {
            return Gson().toJson(dataList)
        }

        @TypeConverter
        fun toList(ListJsonStr: String): AddressVO {
            val dataListType = object : TypeToken<AddressVO>() {}.type
            return Gson().fromJson(ListJsonStr, dataListType)
        }
    }
