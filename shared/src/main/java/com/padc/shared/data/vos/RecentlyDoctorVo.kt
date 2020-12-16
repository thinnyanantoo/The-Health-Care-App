package com.padc.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class RecentlyDoctorVo (
    var id: String? = "",
    var name: String = "",
    var phoneNumber: String? = "",
    var address: String? = "",
    var degree: String? = "",
    var photo: String? = "",
    var deviceId : String? = "",
    var specialityName: String = ""

)

fun MutableMap<String,Any>?.convertToRecentlyDoctorVO() : RecentlyDoctorVo {
    val recentlyDoctorVo = RecentlyDoctorVo()
    recentlyDoctorVo.id = this?.get("id") as String
    recentlyDoctorVo.name = this?.get("name") as String
    recentlyDoctorVo.address = this?.get("address")as String
    recentlyDoctorVo.phoneNumber = this?.get("phone-number")as String
    recentlyDoctorVo.degree = this["degree"]as String
    recentlyDoctorVo.deviceId = this["device-id"]as String
    recentlyDoctorVo.specialityName = this?.get("speciality-name") as String
    return recentlyDoctorVo
}