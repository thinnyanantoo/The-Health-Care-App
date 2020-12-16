package com.padc.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.PropertyName

@IgnoreExtraProperties
@Entity(tableName = "specialitytable")
class SpecialityVO(
    @PrimaryKey
    var id: String = "",
    @get:PropertyName("speciality-name")  var specialityName: String = "",
    @get:PropertyName("specialQuestion")  var specialQuestion : SpecialQuestionVO = SpecialQuestionVO(),
    @get:PropertyName("medicine") @set:PropertyName("medicine")var medicine: MedicineVO = MedicineVO(),
    var photo : String?  = ""

)

fun MutableMap<String, Any>.convertToSpecialityVO(): SpecialityVO{
    val specialityVo = SpecialityVO()
    specialityVo.id = this.get("id") as String
    specialityVo.specialityName = this["speciality-name"] as String
    specialityVo.specialQuestion = this["special-question"] as SpecialQuestionVO
    specialityVo.photo = this["photo"] as String
    specialityVo.medicine = this["medicine"] as MedicineVO
    return specialityVo}
