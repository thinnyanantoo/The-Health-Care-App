package com.padc.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class DeliveryRoutineVO(
    var id: String = "",
    var deliverDate : String? = ""
)