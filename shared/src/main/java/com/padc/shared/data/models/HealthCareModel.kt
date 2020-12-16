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

    fun getDoctorbyEmail(email: String): LiveData<DoctorVO>

   // fun addPatient(patientVO: PatientVO, onSuccess: () -> Unit, onError: (String) -> Unit)

    fun registerNewDoctor(
        doctorVO: DoctorVO,
        onSuccess: (doctorVo : DoctorVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun registerNewPatient(
        patientVO: PatientVO,
        onSuccess: (patientVo: PatientVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPatientFromFirebaseApiAndSaveToDatabase(
        onSuccess: (List<PatientVO>) -> Unit,
        onError: (String) -> Unit
    )

    fun insertCaseSummary(caseSummaryVO: List<CaseSummaryVO>)

    fun deleteCaseSummary()

    fun getCaseSummaryFromDb(onError: (String) -> Unit): LiveData<List<CaseSummaryVO>>

    fun insertDatatoPatientVO(patientVO: PatientVO)

    fun deletePatient(patientVO: PatientVO)
    fun getPatientById(Id: String): LiveData<PatientVO>
    fun getPatientNameById(Id: String): LiveData<PatientVO>

    fun getPatientFromDb(onError: (String) -> Unit): LiveData<PatientVO>


    fun getSpecialitiesFromFirebaseApiAndSaveToDatabase(
        onSuccess: (List<SpecialityVO>) -> Unit,
        onError: (String) -> Unit
    )

    fun getSpecialities(onError: (String) -> Unit): LiveData<List<SpecialityVO>>


    fun getSpecialQuestionBySpecialityNameFromFirebaseAndSaveToDatabase(
        specialityId: String,
        onSuccess: (List<SpecialQuestionVO>) -> Unit,
        onError: (String) -> Unit
    )


    fun addBroadCastConsultationRequest(
        patientVO: PatientVO,
        caseSummaryVO: List<CaseSummaryVO>,
        specialityName: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getBroadCastConsultationRequest(
        specialityName: String,
        onSuccess: (consultationRequestVO: List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    )




    fun addOneTimeGeneralQuestionToPatient(id: String, question: String, answer: String)

    fun addCaseSummaryToConsultationRequestToBroadCase(question: String, answer: String)

    fun getSpecialQuestionBySpecialityNameFromDatabase(): LiveData<List<SpecialQuestionVO>>


    fun getPrescribtion(documentId: String, presriptionVO: List<PresriptionVO>)

    fun addedToPrescription(documentId: String, presriptionVO: PresriptionVO)

    fun addToCheckOut(
        prescription: List<PresriptionVO>,
        deliveryRoutineVO: DeliveryRoutineVO,
        id: String,
        address: String
    )

    fun getAllGeneralQuestion(onError: (String) -> Unit): LiveData<GeneralQuestionVO>

    fun getGeneralQuestionFromFirebaseApiAndSaveToDataBase(
        onSuccess: (generalQuestionVO: List<GeneralQuestionVO>) -> Unit,
        onError: (String) -> Unit
    )


    fun getConsultation(
        onSuccess: (consultation: List<ConsultationVO>) -> Unit,
        onError: (String) -> Unit
    )


    fun getMedicineToPrescribe(
        specialityVO: SpecialityVO,
        specialityName: String,
        onSuccesss: (medicineVO: MedicineVO) -> Unit,
        onError: (String) -> Unit
    )


}