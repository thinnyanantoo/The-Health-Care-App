package com.padc.shared.data.models

import androidx.lifecycle.LiveData
import com.padc.shared.data.vos.*
import com.padc.shared.network.FirebaseApi

interface HealthCareModel {

    var mFirebaseApi: FirebaseApi

    fun getDoctorFromFirebaseApiAndSaveToDatabase(
        onSuccess: (List<DoctorVO>) -> Unit,
        onError: (String) -> Unit
    )

    fun getDoctor(onError: (String) -> Unit): LiveData<DoctorVO>

    fun getPatientFromFirebaseApiAndSaveToDatabase(
        onSuccess: (List<PatientVO>) -> Unit,
        onError: (String) -> Unit
    )

    fun getPatient(onError: (String) -> Unit): LiveData<PatientVO>

    fun getSpecialitiesFromFirebaseApiAndSaveToDatabase(
        onSuccess: (List<SpecialityVO>) -> Unit,
        onError: (String) -> Unit
    )

    fun getSpecialities(onError: (String) -> Unit): LiveData<List<SpecialityVO>>

    fun getPrescribeMedicineFromFirebaseApiAndSaveToDatabase(
        onSuccess: (List<MedicineVO>) -> Unit,
        onError: (String) -> Unit
    )

    fun getPrescribeMedicine(
        onError: (String) -> Unit
    ): LiveData<MedicineVO>

    fun addToCheckOut(medicineId : String, name : String, price : Int, qty : Int)

    fun getCheckOutItem(onSuccess: (List<CheckOutVO>) -> Unit, onFailure : (String) -> Unit)

    fun clearChart()


}