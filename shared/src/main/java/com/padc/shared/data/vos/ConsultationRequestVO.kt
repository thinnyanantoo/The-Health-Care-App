package com.padc.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "consultationRequestTable")
data class ConsultationRequestVO (
    var caseSummaryVO: ArrayList<CaseSummaryVO>? = arrayListOf(),
    var doctorVO: DoctorVO ? = null,
    var patientVO: PatientVO ? = null,
    var specialityName : String ? = null,
    var specialityId : String ? = null,
    var status : String? = null,
    @PrimaryKey
    var id : String = ""
)

fun MutableMap<String,Any>?.convertToConsultationRequestVo() : ConsultationRequestVO{
    var consultationRequestVO = ConsultationRequestVO()
    consultationRequestVO.id= this?.get("id")as String
    consultationRequestVO.status = this?.get("status")as String
    consultationRequestVO.specialityName = this?.get("speciality-name")as String
    consultationRequestVO.specialityId = this?.get("speciality-id")as String
    consultationRequestVO.caseSummaryVO = this?.get("case-summary")as ArrayList<CaseSummaryVO>
   consultationRequestVO.doctorVO= toConvertDoctor((this?.get("doctor") as HashMap<String, String>? ))
    consultationRequestVO.patientVO = toConvertPatient((this?.get("patient")  as  HashMap<String, String>))
    return consultationRequestVO

}

fun toConvertPatient(data: HashMap<String, String>?): PatientVO? {
    data?.let {
        val patient = PatientVO()
        patient.photo = data.get("photo").toString()
        patient.id = data.get("id").toString()
        patient.pname = data.get("pname").toString()
        patient.email = data.get("email").toString()
        patient.password = data.get("password").toString()
        patient.weight = data.get("weight").toString()
        patient.height = data.get("height").toString()
        patient.allergicMedicine = data.get("allegicMedicine").toString()
        patient.DOB = data.get("dob").toString()
        patient.deviceId = data.get("deviceId").toString()
        patient.bloodPressure = data.get("bloodPressure").toString()
        patient.bloodType = data.get("bloodType").toString()
        return patient
    }
    return null
}


fun toConvertDoctor(data : HashMap<String, String>?) : DoctorVO?{
    data?.let{
        val doctor = DoctorVO()
        doctor.name = data.get("name").toString()
        doctor.photo = data.get("photo").toString()
        doctor.biography = data.get("biography").toString()
        doctor.specialityName = data.get("specialityName").toString()
        doctor.degree = data.get("degree").toString()
        doctor.email = data.get("email").toString()
        doctor.phoneNumber = data.get("phoneNumber").toString()
        doctor.password = data.get("password").toString()
        doctor.experience = data.get("experience").toString()
        return doctor
    }
    return null


}







