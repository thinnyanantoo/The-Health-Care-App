package com.padc.shared.data.vos

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class AddressVO (
    var state: String? = "",
    var street : String? = "",
    var township : String? = "",
    var fullAddress: String? = "",
    @Exclude
    var isSelect: Boolean? = false,
    @Exclude
    var isAlreadySelect : Boolean? = false
)
