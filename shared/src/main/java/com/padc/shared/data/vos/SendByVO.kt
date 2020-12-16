package com.padc.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class SendByVO(
    var name : String = "",
    var photo : String = ""
)