package com.padc.shared.data.vos

import androidx.room.Entity
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "consultationTable")
class ConsultationVO(
    var chatMessage: ArrayList<String> = arrayListOf(),
    var prescription: PresriptionVO = PresriptionVO(),
    var caseSummary: CaseSummaryVO = CaseSummaryVO(),
    var doctor: DoctorVO = DoctorVO(),
    var patient: PatientVO = PatientVO(),
    var id: String = ""
)