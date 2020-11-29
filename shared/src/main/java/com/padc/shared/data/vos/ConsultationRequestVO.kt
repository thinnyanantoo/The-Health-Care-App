package com.padc.shared.data.vos

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "consultationRequest")
data class ConsultationRequestVO(
    var caseSummary: CaseSummaryVO = CaseSummaryVO(),
    var data: String = "",
    var doctor: DoctorVO = DoctorVO(),

    @PrimaryKey
    var id: String = "",
    var patient: PatientVO = PatientVO()
)