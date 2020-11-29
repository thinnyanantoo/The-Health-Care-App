package com.padc.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class PresriptionVO(
    var id: String = "",
    var name: String = "",
    var price: String = "",
    var routineVO: RoutineVO = RoutineVO()
)