package com.padc.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "medicine")
class MedicineVO(
    @PrimaryKey
    var id: String = "",
    var mname: String = "",
    var price: String = ""
)