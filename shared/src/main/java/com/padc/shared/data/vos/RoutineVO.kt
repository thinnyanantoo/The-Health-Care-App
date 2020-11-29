package com.padc.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class RoutineVO(
    var id: String = "",
    var name: String = "",
    var type: String = "",
    var time: Int = 0
)