package com.padc.shared.data.vos

import android.widget.GridLayout
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "specialitytable")
class SpecialityVO(
    @PrimaryKey
    var id: String = "",
    var specialityName: String = "",
    var specialQuestion: ArrayList<SpecialQuestionVO> = arrayListOf(),
    var medicine: List<MedicineVO> = listOf()

)
