package com.padc.shared.data.models.impls

import android.util.Log
import androidx.lifecycle.LiveData
import com.padc.shared.data.models.BaseModel
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.vos.*
import com.padc.shared.network.CloudFirebaseStoreFirebaseApiImpl
import com.padc.shared.network.FirebaseApi
import java.text.SimpleDateFormat
import java.util.*

object HealthCareModelImpl : HealthCareModel, BaseModel() {

    override var mFirebaseApi: FirebaseApi = CloudFirebaseStoreFirebaseApiImpl

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

    override fun insertCaseSummary(caseSummaryVO: List<CaseSummaryVO>) : String {
        val id = UUID.randomUUID().toString()
        mTheDB.caseSummaryDao().insertCaseSummary(caseSummaryVO)
        return id
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
        documentId: String,
        patientVO: PatientVO,
        caseSummaryVO: List<CaseSummaryVO>,
        specialityName: String,
        speicalityId: String,
        onSuccess: (id : String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.broadCastConsultationRequest(
            documentId,
            patientVO,
            caseSummaryVO,
            specialityName,
            speicalityId,
            {
               onSuccess(
                   it
               )
            },
            {})
    }

    override fun getConsultationByPatient(
        id: String,
        onSuccess: (List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        return mFirebaseApi.getConsultationByPatient(id, onSuccess, onFailure)
    }

    override fun getConsultationConfirmByPatient(
        id: String,
        onSuccess: (consultationRequest: ConsultationRequestVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getConfirmConsultationRequest(id, onSuccess, onFailure)
    }

    ////////////////////////////////////////////////////////////////////////////DOCTOR

    override fun getDoctorFromFirebaseApiAndSaveToDatabase(
        email: String,
        onSuccess: (List<DoctorVO>) -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getDoctorFromFirestore(email, onSuccess = {
            mTheDB.doctorDao().deleteAll()
            mTheDB.doctorDao().insetDoctor(it)
        }, onFailure = {
            onError
        })
    }

    override fun getDoctorbyEmail(email: String): LiveData<DoctorVO> {
        return mTheDB.doctorDao().getDoctorById(email)
    }

    override fun getAllChatMessage(
        consultationId: String,
        onSuccess: (message: List<ChatMessageVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getMessageChart(consultationId, onSuccess, onFailure)
    }

    override fun getConsultationRequestByIdAndAddDoctor(
        id: String,
        doctor: DoctorVO,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        return mFirebaseApi.getConsultationRequestByIdAndAddDoctor(id, onSuccess = { consultation ->
            val dateFormat = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss: a")
            val currentDate: String = dateFormat.format(Date())
            consultation.patientVO?.let { patient ->
                consultation.caseSummaryVO?.toList()?.let { caseSummary ->
                    mFirebaseApi.startConsultation(
                        caseSummary,
                        id,
                        currentDate,
                        patient,
                        doctor,
                        onSuccess = {
                            onSuccess(it)
                        },
                        onFailure = {
                            onFailure(it)
                        })
                }
            }
        },onFailure = {})
    }

    override fun getBroadCastConsultationRequestFromFireStoreAndSaveToDatabase(
        specialityName: String,
        onSuccess: (consultationRequestVO: List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getBrodaCastConsultationRequest(specialityName, onSuccess = {
            mTheDB.consultationRequestDao().deleteAll()
            mTheDB.consultationRequestDao().insertConsultationRequest(it)
            Log.d("Succcess", "SuccessgetFromFirebase")
        }, onFailure = {})
    }

    override fun deleteRequestFromDatabase() {
        //   mTheDB.consultationRequestDao().deleteAll()
    }

    override fun getConsultationRequestWhenStatusNew(status: String): LiveData<ConsultationRequestVO> {
        return mTheDB.consultationRequestDao().getConsultationByStatus(status)
    }

    override fun getRequestFromDatabase(onError: (String) -> Unit): LiveData<List<ConsultationRequestVO>> {
        return mTheDB.consultationRequestDao().getconsultationRequest()
    }

    override fun getRequestCaseSummaryById(requestID: String): LiveData<ConsultationRequestVO> {
        return mTheDB.consultationRequestDao().getConsultationRequestByID(requestID)
    }

    override fun addOneTimeGeneralQuestionToPatient(id: String, question: String, answer: String) {
        mFirebaseApi.addOneTimeGeneralQuestionToPatient(id, question, answer)
    }

    override fun getCaseSummaryFromConsultation(
        requestid: String,
        onSuccess: (caseSummary: List<CaseSummaryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getCaseSummaryFromConsultation(requestid,onSuccess = onSuccess,onFailure = onFailure)
    }

    override fun getPatientFromConsultation(
        requestid: String,
        onSuccess: (PatientVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getPatientFromConsultation(requestid,onSuccess, onFailure)
    }

    override fun getCaseSummaryFromRequst(
        requestid: String,
        onSuccess: (caseSummary: List<CaseSummaryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getCaseSummaryFromRequest(requestid,onSuccess, onFailure)
    }

    override fun getPatientFromRequest(
        requestid: String,
        onSuccess: (PatientVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getPatientFromRequest(requestid, onSuccess, onFailure)
    }


//    override fun addOneTimeGeneralQuestionToPatient(patientVO: PatientVO,id: String, question: String, answer: String) {
//       mFirebaseApi.addOneTimeGeneralQuestionToPatient(patientVO,id,question, answer)
//    }

    override fun getSpecialQuestionBySpecialityNameFromDatabase(): LiveData<List<SpecialQuestionVO>> {
        return mTheDB.specialQuestionDao().getSpecialQuestion()
    }

    override fun getMedicineBySpecialityIdFromFirebaseAndSaveToDatabase(
        specialityId: String,
        onSuccess: (List<MedicineVO>) -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getMedicineBySpecialityId(specialityId,
            onSuccess = {
                mTheDB.medicineDao().deleteAll()
                mTheDB.medicineDao().insertMedicine(it)
            }, onFailure = {
                onError(it)
            })
    }

    override fun getMedicineBySpeciaityIdFromDatabase(): LiveData<List<MedicineVO>> {
        return mTheDB.medicineDao().getMedicine()
    }

    override fun getPrescribtion(documentId: String, presriptionVO: List<PresriptionVO>) {
        mFirebaseApi.getPrescription(documentId, onSuccess = {
            it
        }, onFailure = {
            error(it)
        })
    }

//    override fun addedToPrescription(documentId: String, presriptionVO: PresriptionVO) {
//        mFirebaseApi.addToPrescription(
//            documentId,
//            presriptionVO.id,
//            presriptionVO.mname,
//            presriptionVO.price,
//            presriptionVO.routine
//        )
//    }

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


    override fun getConsultation(
        onSuccess: (consultation: List<ConsultationVO>) -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getConsultation(onSuccess, onError)
    }

    override fun getConsultationByDoctor(
        id: String,
        onSuccess: (consultationVo: List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        return mFirebaseApi.getConsultationByDoctor(id, onSuccess, onFailure)
    }

    override fun addConsultation(
        consultationId: String,
        dateTime: String,
        caseSummary: List<CaseSummaryVO>,
        patientVO: PatientVO,
        doctorVO: DoctorVO,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.startConsultation(
            caseSummary,
            consultationId,
            dateTime,
            patientVO,
            doctorVO,
            onSuccess,
            onFailure
        )
    }

//    override fun getMedicineToPrescribe(
//        specialityVO: SpecialityVO,
//        specialityName: String,
//        onSuccesss: (medicineVO: MedicineVO) -> Unit,
//        onError: (String) -> Unit
//    ) {
//        mFirebaseApi.getMedicinetoPrescribe(
//            specialityVO,
//            specialityName,
//            onSuccess = {},
//            onFailure = {})
//    }

    override fun sendMessageBySender(
        id: String,
        messageVO: ChatMessageVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.sendMessage(id, messageVO, onSuccess, onFailure)
    }


//    override fun getConsultationChat(consulationId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
//        mFirebaseApi.getConsulationChatById(consulationId,
//            onSuccess = {
//                mTheDB.consultationChatDao().deleteAllConsultationChatData()
//                mTheDB.consultationChatDao().insertConsultationChatData(it)
//            }, onFailure = { onError(it) })
//    }
//
//    override fun getConsultationChatFromDB(consulationId: String): LiveData<ConsultationChatVO> {
//        return mTheDB.consultationChatDao().getAllConsultationChatDataBy(consulationId)
//    }


    override fun finishConsultation(
        consultationVO : ConsultationVO,
        prescriptionList: List<PresriptionVO>,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.finishConsultation(consultationVO,prescriptionList,onSuccess,onError)
    }

    override fun getMedicineBySpeciality(
        speciality: String,
        onSuccess: (medicine: List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getMedicineBySpeciality(speciality,onSuccess,onFailure)
    }


    override fun getConsultationById(
        id: String,
        onSuccess: (ConsultationVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        return mFirebaseApi.getConsultationById(id,onSuccess,onFailure)
    }

}