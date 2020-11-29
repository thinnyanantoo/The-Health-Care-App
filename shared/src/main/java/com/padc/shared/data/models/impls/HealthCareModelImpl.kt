package com.padc.shared.data.models.impls

import androidx.lifecycle.LiveData
import com.padc.shared.data.models.BaseModel
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.vos.*
import com.padc.shared.network.CloudFirebaseStoreFirebaseApiImpl
import com.padc.shared.network.FirebaseApi

object HealthCareModelImpl : HealthCareModel, BaseModel(){

    override var mFirebaseApi: FirebaseApi = CloudFirebaseStoreFirebaseApiImpl
    override fun getDoctorFromFirebaseApiAndSaveToDatabase(
        onSuccess: (List<DoctorVO>) -> Unit,
        onError: (String) -> Unit
    ) {
       mFirebaseApi.getAllDoctor(onSuccess = {
           mTheDB.doctorDao().insetDoctor(it)
       }, onFailure = {
           onError
       })
    }

    override fun getDoctor(onError: (String) -> Unit): LiveData<DoctorVO> {
        return mTheDB.doctorDao().getDoctorsLit()
    }

    override fun getPatientFromFirebaseApiAndSaveToDatabase(
        onSuccess: (List<PatientVO>) -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getPatient(onSuccess = {
            mTheDB.patientDao().insertPatient(it)
        },onFailure = {
            onError
        })
    }

    override fun getPatient(onError: (String) -> Unit): LiveData<PatientVO> {
        return mTheDB.patientDao().getPatient()
    }

    override fun getSpecialitiesFromFirebaseApiAndSaveToDatabase(
        onSuccess: (List<SpecialityVO>) -> Unit,
        onError: (String) -> Unit
    ) {
       mFirebaseApi.getSpecialities(onSuccess = {
           mTheDB.spelicityDao().insertSpeicality(it)
       },onFailure = {
           onError
       })
    }

    override fun getSpecialities(onError: (String) -> Unit): LiveData<List<SpecialityVO>> {
       return mTheDB.spelicityDao().getSpecialityList()
    }

    override fun getPrescribeMedicineFromFirebaseApiAndSaveToDatabase(
        onSuccess: (List<MedicineVO>) -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getPrescribeMedicine(onSuccess= {
            mTheDB.medicineDao().insertMedicine(it)
        },onFailure = {
            onError
        })
    }

    override fun getPrescribeMedicine(onError: (String) -> Unit): LiveData<MedicineVO> {
       return mTheDB.medicineDao().getMedicine()
    }

    override fun addToCheckOut(medicineId: String, name: String, price: Int, qty: Int) {
        mFirebaseApi.addToCheckOut(medicineId,name,price,qty)
    }

    override fun getCheckOutItem(
        onSuccess: (List<CheckOutVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
       mFirebaseApi.getCheckOutItem(onSuccess,onFailure)
    }

    override fun clearChart() {
       mFirebaseApi.clearCheckOut()
    }
}