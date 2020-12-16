package com.padc.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class PresriptionVO(
    var id: String = "",
    var mname: String = "",
    var price : String = "",
    var routine : RoutineVO = RoutineVO()
)

fun MutableMap<String,Any>?.convertToPrescriptionVO() : PresriptionVO {
    val prescriptionVO = PresriptionVO()
    prescriptionVO.id = this?.get("id") as String
    prescriptionVO.mname = this?.get("mname") as String
    prescriptionVO.price = this?.get("price") as String
    prescriptionVO.routine = this?.get("routine") as RoutineVO
    return prescriptionVO
}

