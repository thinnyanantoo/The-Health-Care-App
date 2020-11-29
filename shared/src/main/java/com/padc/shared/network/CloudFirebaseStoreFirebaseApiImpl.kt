package com.padc.shared.network

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import com.padc.shared.data.vos.*

object CloudFirebaseStoreFirebaseApiImpl : FirebaseApi {
    val db = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()
    val storageReference = storage.reference
    override fun getAllDoctor(
        onSuccess: (doctors: List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("doctor").addSnapshotListener { value, error ->
            error?.let {
                onFailure(it.message ?: "Please Check connection")
            } ?: run {
                val doctorList: MutableList<DoctorVO> = arrayListOf()
                val result = value?.documents ?: arrayListOf()

                for (document in result) {
                    val doctor = document.data.convertToDoctorVO()
                    doctorList.add(doctor)
                }
                onSuccess(doctorList)

            }
        }
    }

    override fun addDoctors(
        doctorVO: DoctorVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val doctorMap = hashMapOf(
            "name" to doctorVO.name,
            "phone number" to doctorVO.phoneNumber,
            "speciality-name" to doctorVO.specialityName,
            "phone-number" to doctorVO.phoneNumber,
            "address" to doctorVO.address,
            "degree" to doctorVO.degree,
            "photo" to doctorVO.photo
        )
        db.collection("doctor")
            .document(doctorVO.name)
            .set(doctorMap)
            .addOnSuccessListener { Log.d("Success", "Successfully added to doctor") }
            .addOnFailureListener { Log.d("Failure", "failed to add doctor") }
    }

    override fun addPatient(
        patientVO: PatientVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val patientMap = hashMapOf(
            "pname" to patientVO.name,
            "DOB" to patientVO.DOB,
            "gender" to patientVO.gender,
            "address" to patientVO.address,
            "photo" to patientVO.photo,
            "recently_doctor" to patientVO.recentlyDoctorVo
        )
        db.collection("patient")
            .document(patientVO.name)
            .set(patientMap)
            .addOnSuccessListener { Log.d("Success", "Successfully added to patient") }
            .addOnFailureListener { Log.d("Failure", "failed to add patient") }
    }

    override fun getPatient(
        onSuccess: (patient: List<PatientVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("patient").addSnapshotListener { value, error ->

            error?.let {
                onFailure(it.message ?: "Please Check connection")
            } ?: run {
                val patientList: MutableList<PatientVO> = arrayListOf()
                val result = value?.documents ?: arrayListOf()

                for (document in result) {
                    val patient = document.data.convertToPatientVO()
                    patientList.add(patient)
                }
                onSuccess(patientList)

            }
        }
    }

    override fun getSpecialities(
        onSuccess: (SpecialityList: List<SpecialityVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("specialities").addSnapshotListener { value, error ->
            error?.let{
                onFailure(it.message ?: "Please Check Connection")
            } ?: run {
                val specialityList : MutableList<SpecialityVO> = arrayListOf()
                val result = value?.documents ?: arrayListOf()

                for(document in result){
                    val hashmap = document.data
                    hashmap?.put("id",document.id)
                    val data = Gson().toJson(hashmap)
                    val dataVo = Gson().fromJson<SpecialityVO>(data,SpecialityVO::class.java)
                    specialityList.add(dataVo)
                }
                   onSuccess(specialityList)
            }
        }
    }

    override fun startConsultation(
        onSuccess: (ConsultationVO: ConsultationVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getRecentlyDoctor(
        onSuccess: (patient : PatientVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getPrescribeMedicine(
        onSuccess: (MedicineVO: List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun finishConsultation(
        onSuccess: (DoctorVO: List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun broadCastConsultationRequest(
        onSuccess: (patientVO: List<PatientVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun checkOutMedicine(
        onSuccess: (checkOutVO: CheckOutVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun sendDirectRequest(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun addToCheckOut(medicineId: String, name: String, price: Int, qty: Int) {
        TODO("Not yet implemented")
    }

    override fun getCheckOutItem(
        onSuccess: (List<CheckOutVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun clearCheckOut() {
        TODO("Not yet implemented")
    }
}