package com.padc.shared.data.models.impls

import android.util.Log
import androidx.lifecycle.LiveData
import com.padc.shared.data.models.BaseModel
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.vos.*
import com.padc.shared.network.CloudFirebaseStoreFirebaseApiImpl
import com.padc.shared.network.FirebaseApi

object HealthCareModelImpl : HealthCareModel, BaseModel() {

    override var mFirebaseApi: FirebaseApi = CloudFirebaseStoreFirebaseApiImpl
    override fun getDoctorFromFirebaseApiAndSaveToDatabase(
        onSuccess: (List<DoctorVO>) -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getDoctorFromFirestore(onSuccess = {
            mTheDB.doctorDao().insetDoctor(it)
        }, onFailure = {
            onError
        })
    }

    override fun getDoctorbyEmail(email: String): LiveData<DoctorVO> {
        return mTheDB.doctorDao().getDoctorById(email)
    }

    override fun registerNewDoctor(
        doctorVO: DoctorVO,
        onSuccess: (doctorVo: DoctorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.addDoctors(doctorVO, onSuccess = {
            onSuccess(doctorVO)
        }, onFailure = {
            onFailure("Error adding doctor")
        })
    }

    override fun registerNewPatient(
        patientVO: PatientVO,
        onSuccess: (patientVo: PatientVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.addPatient(patientVO, onSuccess = {
            onSuccess(patientVO)
        }, onFailure = {
            onFailure(it)
        })
    }


    override fun getPatientFromFirebaseApiAndSaveToDatabase(
        onSuccess: (List<PatientVO>) -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getPatientFromFirestore(onSuccess = {
            mTheDB.patientDao().deleteAll()
            mTheDB.patientDao().insertPatient(it)
        }, onFailure = {
            onError
        })
    }

    override fun insertCaseSummary(caseSummaryVO: List<CaseSummaryVO>) {
        mTheDB.caseSummaryDao().insertCaseSummary(caseSummaryVO)
    }

    override fun deleteCaseSummary() {
        mTheDB.caseSummaryDao().deleteAll()
    }

    override fun getCaseSummaryFromDb(onError: (String) -> Unit): LiveData<List<CaseSummaryVO>> {
        return mTheDB.caseSummaryDao().getCaseSummary()
    }

    override fun insertDatatoPatientVO(patientVO: PatientVO) {
        mTheDB.patientDao().deleteAll()
        mTheDB.patientDao().insertDataToPatient(patientVO)
        Log.d("patientVo", patientVO.bloodType.toString())
        Log.d("patienVo", patientVO.bloodPressure.toString())
    }

    override fun deletePatient(patientVO: PatientVO) {
        mTheDB.patientDao().deletePATIENT(patientVO)
    }


    override fun getPatientById(Id: String): LiveData<PatientVO> {
        return mTheDB.patientDao().getPatientById(Id)
    }

    override fun getPatientNameById(Id: String): LiveData<PatientVO> {
        TODO("Not yet implemented")
    }


    override fun getPatientFromDb(onError: (String) -> Unit): LiveData<PatientVO> {
        return mTheDB.patientDao().getPatient()
    }

    override fun getSpecialitiesFromFirebaseApiAndSaveToDatabase(
        onSuccess: (List<SpecialityVO>) -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getSpecialities(onSuccess = {
            // mTheDB.spelicityDao().deleteAll()
            mTheDB.spelicityDao().insertSpeicality(it)
        }, onFailure = {
            onError
        })
    }

//    override fun getGeneralQuestionFromFirebaseApiAndSaveToDatabase(
//        id : String,
//        onSuccess: (List<GeneralQuestionVO>) -> Unit,
//        onError: (String) -> Unit
//    ) {
//        mFirebaseApi.getGeneralQuestion(id,onSuccess = {
//            mTheDB.generalQuesionDao().insertGeneralQuestion(it)
//        },onFailure = {
//            error(it)
//        })
//    }

    override fun getSpecialities(onError: (String) -> Unit): LiveData<List<SpecialityVO>> {
        return mTheDB.spelicityDao().getSpecialityList()
    }

    override fun getSpecialQuestionBySpecialityNameFromFirebaseAndSaveToDatabase(
        specialityId: String,
        onSuccess: (List<SpecialQuestionVO>) -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getSpecialQuestionBySpecialityName(specialityId,
            onSuccess = {
                mTheDB.specialQuestionDao().deleteSpecialQuestion(it)
                mTheDB.specialQuestionDao().insertSpecialQuestion(it)
            }, onFailure = {
                onError(it)
            })
    }

    override fun addBroadCastConsultationRequest(
        patientVO: PatientVO,
        caseSummaryVO: List<CaseSummaryVO>,
        specialityName: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.broadCastConsultationRequest(patientVO, caseSummaryVO, specialityName, {}, {})
    }


    override fun getBroadCastConsultationRequest(
        specialityName: String,
        onSuccess: (consultationRequestVO: List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getBrodaCastConsultationRequest(specialityName, onSuccess = {
            Log.d("Succcess","SuccessgetFromFirebase")

        }, onFailure = {})
    }


    override fun addOneTimeGeneralQuestionToPatient(id: String, question: String, answer: String) {
        mFirebaseApi.addOneTimeGeneralQuestionToPatient(id, question, answer)
    }

    override fun addCaseSummaryToConsultationRequestToBroadCase(question: String, answer: String) {
        //   mFirebaseApi.addToCaseSummaryToBroadCastRequest(question,answer)
    }

//    override fun addOneTimeGeneralQuestionToPatient(patientVO: PatientVO,id: String, question: String, answer: String) {
//       mFirebaseApi.addOneTimeGeneralQuestionToPatient(patientVO,id,question, answer)
//    }

    override fun getSpecialQuestionBySpecialityNameFromDatabase(): LiveData<List<SpecialQuestionVO>> {
        return mTheDB.specialQuestionDao().getSpecialQuestion()
    }

    override fun getPrescribtion(documentId: String, presriptionVO: List<PresriptionVO>) {
        mFirebaseApi.getPrescription(documentId, onSuccess = {
            it
        }, onFailure = {
            error(it)
        })
    }

    override fun addedToPrescription(documentId: String, presriptionVO: PresriptionVO) {
        mFirebaseApi.addToPrescription(
            documentId,
            presriptionVO.id,
            presriptionVO.mname,
            presriptionVO.price,
            presriptionVO.routine
        )
    }

    override fun addToCheckOut(
        prescription: List<PresriptionVO>,
        deliveryRoutineVO: DeliveryRoutineVO,
        id: String,
        address: String
    ) {
        mFirebaseApi.checkOutMedicine(
            prescription,
            deliveryRoutineVO,
            id,
            address,
            onSuccess = {},
            onFailure = {})
    }

    override fun getAllGeneralQuestion(onError: (String) -> Unit): LiveData<GeneralQuestionVO> {
        return mTheDB.generalDao().getGeneralQuestion()
    }


    override fun getGeneralQuestionFromFirebaseApiAndSaveToDataBase(
        onSuccess: (generalQuestionVO: List<GeneralQuestionVO>) -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getGeneralQuestion(onSuccess = {
            mTheDB.generalDao().deleteGeneralQuestion(it)
            mTheDB.generalDao().insertGeneralQuestion(it)
        }, onFailure = {

        })
    }

//    override fun getGeneralOneTimeQuestion(question : String):LiveData<GeneralQuestionVO> {
//        return mTheDB.generalDao().getGeneralQuestionType("one-time")
//    }
//
//    override fun getGeneralAlwaysQuestion(
//        question: String
//    ): LiveData<GeneralQuestionVO> {
//        return mTheDB.generalDao().getGeneralQuestionType("always")
//    }

    override fun getConsultation(
        onSuccess: (consultation: List<ConsultationVO>) -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getConsultation(onSuccess, onError)
    }

    override fun getMedicineToPrescribe(
        specialityVO: SpecialityVO,
        specialityName: String,
        onSuccesss: (medicineVO: MedicineVO) -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getMedicinetoPrescribe(
            specialityVO,
            specialityName,
            onSuccess = {},
            onFailure = {})
    }


}