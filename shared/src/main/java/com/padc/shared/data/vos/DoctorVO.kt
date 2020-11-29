package com.padc.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "doctor")
data class DoctorVO(
    @PrimaryKey
    var id: String = "",
    var name: String = "",
    var phoneNumber: String? = "",
    var address: String? = "",
    var degree: String? = "",
    var photo: String? = "",
    var specialityName: String? = ""

)

fun MutableMap<String, Any>?.convertToDoctorVO(): DoctorVO {
    val doctorVo = DoctorVO()
    doctorVo.id = this?.get("id") as String
    doctorVo.name = this["name"] as String
    doctorVo.phoneNumber = this["phone-number"] as String
    doctorVo.specialityName = this["speciality-name"] as String
    doctorVo.degree = this["degree"] as String
    doctorVo.address = this["address"] as String
    doctorVo.photo = this["photo"] as String

    return doctorVo
}