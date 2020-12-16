package com.padc.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class AddressVO (
    var newAddress :String? = "",
    var originAddress : String? = ""
)
