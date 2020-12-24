package com.padc.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "checkout")
class CheckOutVO(
    @PrimaryKey
    var id: String = "",
    var address: String = "",
    var prescription: ArrayList<PresriptionVO> = arrayListOf(),
    var deliveryRoutine: DeliveryRoutineVO ?= null,
    var patientVO: PatientVO? = null,
    var doctorVO: DoctorVO?  = null,
    var totalPrice : String = ""

)