package com.padc.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "patient")
data class PatientVO(
    @PrimaryKey
    var id: String = "",
    var DOB: String? = "",
    var photo: String? = "",
    var gender: String? = "",
    var name: String = "",
    var address: String = "",
    var recentlyDoctorVo: List<RecentlyDoctorVo> = emptyList()
)

fun MutableMap<String, Any>?.convertToPatientVO(): PatientVO {
    val patientVo = PatientVO()
    patientVo.id = this?.get("id") as String
    patientVo.name = this["pname"] as String
    patientVo.DOB = this["DOB"] as String
    patientVo.photo = this["photo"] as String
    patientVo.gender = this["gender"] as String
    patientVo.address = this["address"] as String
    patientVo.recentlyDoctorVo = this["recently_doctor"] as String as List<RecentlyDoctorVo>

    return patientVo

}