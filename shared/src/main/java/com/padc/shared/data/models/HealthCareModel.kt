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

    fun insertCaseSummary(caseSummaryVO: List<CaseSummaryVO>) : String

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

    fun getMedicineBySpecialityIdFromFirebaseAndSaveToDatabase(
        specialityId: String,
        onSuccess: (List<MedicineVO>) -> Unit,
        onError: (String) -> Unit
    )

    fun getMedicineBySpeciaityIdFromDatabase(): LiveData<List<MedicineVO>>

    fun addOneTimeGeneralQuestionToPatient(id: String, question: String, answer: String)

    fun getSpecialQuestionBySpecialityNameFromDatabase(): LiveData<List<SpecialQuestionVO>>


    fun getAllGeneralQuestion(onError: (String) -> Unit): LiveData<GeneralQuestionVO>

    fun getGeneralQuestionFromFirebaseApiAndSaveToDataBase(
        onSuccess: (generalQuestionVO: List<GeneralQuestionVO>) -> Unit,
        onError: (String) -> Unit
    )


    fun addBroadCastConsultationRequest(
        documentId: String,
        patientVO: PatientVO,
        caseSummaryVO: List<CaseSummaryVO>,
        specialityName: String,
        specialityId: String,
        onSuccess: (id : String) -> Unit,
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

////////////////////////////////////////////////////////////////////////////


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

    fun deleteRequestFromDatabase()

    fun getRequestFromDatabase(onError: (String) -> Unit): LiveData<List<ConsultationRequestVO>>

    fun getRequestCaseSummaryById(requestID: String): LiveData<ConsultationRequestVO>

    fun getConsultationRequestWhenStatusNew(status: String): LiveData<ConsultationRequestVO>


    fun getPrescription(consultationId: String, onSuccess: (prescription :List<PresriptionVO>) -> Unit,onFailure: (String) -> Unit)

  //  fun addedToPrescription(documentId: String, presriptionVO: PresriptionVO)

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

    fun getConsultationRequestByIdAndAddDoctor(
        id: String,
        doctor: DoctorVO,
        onSuccess: (String) -> Unit,
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

    fun getPatientFromConsultation(
        requestid: String,
        onSuccess: (PatientVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCaseSummaryFromConsultation(
        requestid: String,
        onSuccess: (caseSummary: List<CaseSummaryVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCaseSummaryFromRequst(
        requestid: String,
        onSuccess: (caseSummary: List<CaseSummaryVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPatientFromRequest(
        requestid: String,
        onSuccess: (PatientVO) -> Unit,
        onFailure: (String) -> Unit
    )

//
//    fun getMedicineToPrescribe(
//        specialityVO: SpecialityVO,
//        specialityName: String,
//        onSuccesss: (medicineVO: MedicineVO) -> Unit,
//        onError: (String) -> Unit
//    )



    fun sendMessageBySender(
        id: String,
        messageVO: ChatMessageVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getAllChatMessage(
        consultationId: String,
        onSuccess: (message: List<ChatMessageVO>) -> Unit,
        onFailure: (String) -> Unit
    )


    fun finishConsultation(
        consultationVO : ConsultationVO,
        prescriptionList: List<PresriptionVO>,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )


    fun getMedicineBySpeciality(
        speciality: String,
        onSuccess: (medicine: List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getConsultationById(
        id: String,
        onSuccess: (ConsultationVO) -> Unit,
        onFailure: (String) -> Unit
    )



}