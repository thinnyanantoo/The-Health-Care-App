package com.padc.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class RoutineVO(
    var id: String = "",
    var day: String = "",
    var type: String = ""
)