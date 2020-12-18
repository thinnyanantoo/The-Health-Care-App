package com.padc.shared.network

import com.padc.shared.data.vos.*
import com.padc.shared.persistence.typeConverters.OneTimeGeneralQuestionTypeConverter

interface FirebaseApi {

    fun getDoctorFromFirestore(
        email: String,
        onSuccess: (doctors: List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun addDoctors(doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addPatient(patientVO: PatientVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun addOneTimeGeneralQuestionToPatient(id: String, question: String, answer: String)
    fun getOneTimeGeneralQuestionFromPatient(
        patientId: String,
        onSuccess: (oneTimeGeneralQuestionVo: List<OnetimeGeneralQuestionVO>) -> Unit
    )

    fun addAddressToPatient(
        id: String,
        addressVO: AddressVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPatientFromFirestore(
        onSuccess: (patient: List<PatientVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSpecialities(
        onSuccess: (SpecialityList: List<SpecialityVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getConfirmConsultationRequest(
        id: String,
        onSuccess: (ConsultationRequestVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun startConsultation(
        caseSummaryVO: List<CaseSummaryVO>,
        id: String,
        dataTime: String,
        patientVO: PatientVO,
        doctorVO: DoctorVO,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    )


    fun addedRecentlyDoctor(
        documentId: String,
        patientVO: PatientVO,
        doctorVo: DoctorVO,
        onSuccess: (patientVO: PatientVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getRecentlyDoctor(
        id: String,
        onSuccess: (recentlyDoctor: List<RecentlyDoctorVo>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun finishConsultation(
        consultation: ConsultationVO,
        documentId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )


    fun broadCastConsultationRequest(
        documentId: String,
        patientVO: PatientVO,
        caseSummaryVO: List<CaseSummaryVO>,
        specialityName: String,
        specialityId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )


    fun getBrodaCastConsultationRequest(
        specialityName: String,
        onSuccess: (consultationrequest: List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getConsultationRequestByIdAndAddDoctor(
        id: String,
        onSuccess: (ConsultationRequestVO) -> Unit,
        onFailure: (String) -> Unit
    )

//    fun addToCaseSummaryToBroadCastRequest(
//        question: String,
//        answer: String
//    )

    fun getConsultationByDoctor(
        id: String,
        onSuccess: (consultation: List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getConsultationByPatient(
        id: String,
        onSuccess: (consultation: List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMessageChart(
        documentId: String,
        onSuccess: (messageVO: List<ChatMessageVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun sendDirectRequest(
        patientVO: PatientVO,
        caseSummaryVO: CaseSummaryVO,
        recentlyDoctor: RecentlyDoctorVo,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getAllMedicine(
        specialityId: String,
        onSuccess: (medicineVO: List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMedicinetoPrescribe(
        specialityVO: SpecialityVO,
        specialityName: String,
        onSuccess: (medicine: MedicineVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getConsultation(
        onSuccess: (consultation: List<ConsultationVO>) -> Unit, onFailure: (String) -> Unit
    )

    fun getPrescription(
        documentId: String,
        onSuccess: (prescription: List<PresriptionVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun addToPrescription(
        documentId: String,
        id: String,
        mname: String,
        price: String,
        routineVO: RoutineVO
    )

    fun sendMessage(
        id: String,
        message: ChatMessageVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getGeneralQuestion(
        onSuccess: (generalQuestionVO: List<GeneralQuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSpecialQuestionBySpecialityName(
        specialityId: String,
        onSuccess: (specialQuestionVO: List<SpecialQuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    )


    //CheckOut

    fun checkOutMedicine(
        prescription: List<PresriptionVO>,
        deliveryRoutineVO: DeliveryRoutineVO,
        id: String,
        address: String,
        onSuccess: (checkOutVO: CheckOutVO) -> Unit,
        onFailure: (String) -> Unit
    )

}