package com.padc.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class RecentlyDoctorVo(
    var degree: String = "",
    var id: String = "",
    var sepcialityName: String = "",
    var name: String = ""
)