package com.padc.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "caseSummaryTable")
class CaseSummaryVO(
    @PrimaryKey
    var id: String = "",
    var question : String? = "",
    var answer : String? = ""
)

