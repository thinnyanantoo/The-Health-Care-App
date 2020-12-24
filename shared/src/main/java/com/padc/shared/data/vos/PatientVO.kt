package com.padc.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties
import com.padc.shared.persistence.typeConverters.AddressTypeConverters

@IgnoreExtraProperties
@Entity(tableName = "patient")
@TypeConverters(AddressTypeConverters::class)
data class PatientVO(
    @PrimaryKey
    var id: String = "",
    var photo: String? = "",
    var pname: String? = "",
    var email : String? = "",
    var password : String? = "",
    var weight : String? = "",
    var height : String? = "",
    var DOB : String? = "",
    var allergicMedicine: String? = "",
    var bloodPressure : String? = "",
    var bloodType : String? = "",
    var deviceId : String ? = "",
     var address: ArrayList<AddressVO> = arrayListOf()
  //  var oneTimeGeneralQuestion: OnetimeGeneralQuestionVO = OnetimeGeneralQuestionVO()
//    var recentlyDoctor: RecentlyDoctorVo? = null
)

fun MutableMap<String,Any>?.convertToPatientVo() : PatientVO{

        val patient = PatientVO()
        patient.photo = this?.get("photo").toString()
        patient.id = this?.get("id").toString()
        patient.pname = this?.get("pname").toString()
        patient.email = this?.get("email").toString()
        patient.password = this?.get("password").toString()
        patient.weight = this?.get("weight").toString()
        patient.height = this?.get("height").toString()
        patient.allergicMedicine = this?.get("allegicMedicine").toString()
        patient.DOB = this?.get("dob").toString()
        patient.deviceId = this?.get("deviceId").toString()
        patient.bloodPressure = this?.get("bloodPressure").toString()
        patient.bloodType = this?.get("bloodType").toString()
    val que : ArrayList<AddressVO>? = arrayListOf()
    val value = this?.get("case-summary") as ArrayList<HashMap<String,Any>>?
    value?.forEach{
        it.convertToAddress()?.let { it1 -> que?.add (it1) }
    }
    patient.address = que!!
    return patient

    }


fun MutableMap<String,Any>?.convertToAddress(): AddressVO?{
    val question = AddressVO()
    question.state = this?.get("question")as String
    question.street = this?.get("answer")as String
    question.township = this?.get("id")as String
    question.fullAddress = this?.get("fullAddress")as String
    return question
}
