package com.padc.shared.data.models

import androidx.lifecycle.LiveData
import com.padc.shared.data.vos.*
import com.padc.shared.network.FirebaseApi

interface HealthCareModel {

    var mFirebaseApi: FirebaseApi

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

    fun addOneTimeGeneralQuestionToPatient(id: String, question: String, answer: String)

    fun getSpecialQuestionBySpecialityNameFromDatabase(): LiveData<List<SpecialQuestionVO>>


    fun getAllGeneralQuestion(onError: (String) -> Unit): LiveData<GeneralQuestionVO>

    fun getGeneralQuestionFromFirebaseApiAndSaveToDataBase(
        onSuccess: (generalQuestionVO: List<GeneralQuestionVO>) -> Unit,
        onError: (String) -> Unit
    )


    fun addBroadCastConsultationRequest(
        patientVO: PatientVO,
        caseSummaryVO: List<CaseSummaryVO>,
        specialityName: String,
        specialityId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getConsultationByPatient(
        id: String,
        onSuccess: (List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getConsultationConfirmByPatient(
        id: String,
        onSuccess: (consultationRequest: ConsultationRequestVO) -> Unit,
        onFailure: (String) -> Unit
    )

////////////////////////////////////////////////////////////////////////////DOCTOR


    fun getDoctorFromFirebaseApiAndSaveToDatabase(
        email: String,
        onSuccess: (List<DoctorVO>) -> Unit,
        onError: (String) -> Unit
    )

    fun getDoctorbyEmail(email: String): LiveData<DoctorVO>

    fun registerNewDoctor(
        doctorVO: DoctorVO,
        onSuccess: (doctorVo: DoctorVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getBroadCastConsultationRequestFromFireStoreAndSaveToDatabase(
        specialityName: String,
        onSuccess: (consultationRequestVO: List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getRequestFromDatabase(onError: (String) -> Unit): LiveData<List<ConsultationRequestVO>>

    fun getRequestCaseSummaryById(requestID: String): LiveData<ConsultationRequestVO>


    fun getPrescribtion(documentId: String, presriptionVO: List<PresriptionVO>)

    fun addedToPrescription(documentId: String, presriptionVO: PresriptionVO)

    fun addToCheckOut(
        prescription: List<PresriptionVO>,
        deliveryRoutineVO: DeliveryRoutineVO,
        id: String,
        address: String
    )


    fun getConsultation(
        onSuccess: (consultation: List<ConsultationVO>) -> Unit,
        onError: (String) -> Unit
    )

    fun getConsultationByDoctor(
        id: String,
        onSuccess: (consultationVo: List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun addConsultation(
        consultationId: String,
        dateTime: String,
        caseSummary: List<CaseSummaryVO>,
        patientVO: PatientVO,
        doctorVO: DoctorVO,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    )


    fun getMedicineToPrescribe(
        specialityVO: SpecialityVO,
        specialityName: String,
        onSuccesss: (medicineVO: MedicineVO) -> Unit,
        onError: (String) -> Unit
    )

    fun sendMessageBySender(
        id: String,
        messageVO: ChatMessageVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

      fun getAllChatMessage(
        consultationId : String,
        onSuccess: (message: List<ChatMessageVO>) -> Unit,
        onFailure: (String) -> Unit
    )


}