package com.padc.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class SpecialQuestionVO(
    var id: String? = "",
    var name: String = ""
)