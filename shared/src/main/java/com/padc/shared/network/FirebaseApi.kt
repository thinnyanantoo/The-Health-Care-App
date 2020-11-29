package com.padc.shared.network

import com.padc.shared.data.vos.*

interface FirebaseApi {

    fun getAllDoctor(onSuccess: (doctors : List<DoctorVO>) -> Unit, onFailure: (String) -> Unit)
    fun addDoctors(doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addPatient(patientVO: PatientVO,onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun getPatient(onSuccess: (patient : List<PatientVO>) -> Unit, onFailure: (String) -> Unit)

    fun getSpecialities(onSuccess : (SpecialityList : List<SpecialityVO>) -> Unit, onFailure: (String) -> Unit)
    fun startConsultation(onSuccess: (ConsultationVO : ConsultationVO) -> Unit, onFailure: (String) -> Unit)
    fun getRecentlyDoctor(onSuccess: (patient : PatientVO) -> Unit, onFailure: (String) -> Unit)

    //Doctor
    fun getPrescribeMedicine(onSuccess: (MedicineVO : List<MedicineVO>) -> Unit, onFailure: (String) -> Unit)
    fun finishConsultation(onSuccess: (DoctorVO : List<DoctorVO>) -> Unit, onFailure: (String) -> Unit)

    //Patient
    fun broadCastConsultationRequest(onSuccess: (patientVO :List<PatientVO>) -> Unit, onFailure: (String) -> Unit)
    fun checkOutMedicine(onSuccess: (checkOutVO : CheckOutVO) -> Unit, onFailure: (String) -> Unit)
    fun sendDirectRequest(onSuccess: () -> Unit,onFailure: (String) -> Unit)

    //CheckOut
    fun addToCheckOut(medicineId : String, name : String, price : Int, qty : Int)
    fun getCheckOutItem(onSuccess : (List<CheckOutVO>) -> Unit, onFailure: (String) -> Unit)
    fun clearCheckOut()
}