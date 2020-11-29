package com.padc.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class CaseSummaryVO
    (
    var id: String = "",
    var answer: String = "",
    var question: String = ""
)