package com.padc.shared.network

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.*
import java.util.*

object CloudFirebaseStoreFirebaseApiImpl : FirebaseApi {
    private val db = Firebase.firestore

    private val storage = FirebaseStorage.getInstance()
    val storageReference = storage.reference

    private val mModel: HealthCareModel = HealthCareModelImpl

    override fun getSpecialities(
        onSuccess: (SpecialityList: List<SpecialityVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("specialities").get().addOnSuccessListener { result ->
            val specialityList: MutableList<SpecialityVO> = arrayListOf()

            for (document in result) {
                val hashmap = document.data
                hashmap?.put("id", document.id)
                val data = Gson().toJson(hashmap)
                val dataVo = Gson().fromJson<SpecialityVO>(data, SpecialityVO::class.java)
                specialityList.add(dataVo)
            }
            onSuccess(specialityList)
        }
    }

    override fun addPatient(
        patientVO: PatientVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val patientMap = hashMapOf(
            "id" to patientVO.id,
            "photo" to patientVO.photo,
            "pname" to patientVO.pname,
            "email" to patientVO.email,
            "password" to patientVO.password,
            "weight" to patientVO.weight,
            "height" to patientVO.height,
            "DOB" to patientVO.DOB,
            "allegicMedicine" to patientVO.allergicMedicine,
            "bloodPressure" to patientVO.bloodPressure,
            "bloodType" to patientVO.bloodType,
            "device-id" to patientVO.deviceId
//            "recently_doctor" to patientVO.recentlyDoctorVo
        )
        db.collection("patient")
            .document(patientVO.id)
            .set(patientMap)
            .addOnSuccessListener { Log.d("Success", "Successfully added to patient") }
            .addOnFailureListener { Log.d("Failure", "failed to add patient") }
    }


    override fun addOneTimeGeneralQuestionToPatient(
        id: String,
        question: String,
        answer: String

    ) {
        val generalQuestionMap = hashMapOf(
            "question" to question,
            "answer" to answer
        )
        db.collection("patient/$id/oneTimeGeneralQuestion")
            .add(generalQuestionMap)
            .addOnSuccessListener { Log.d("Success", "Success added") }
            .addOnFailureListener { Log.d("failure", "Failed to add") }
    }

    override fun getOneTimeGeneralQuestionFromPatient(
        patientId: String,
        onSuccess: (oneTimeGeneralQuestionVo: List<OnetimeGeneralQuestionVO>) -> Unit
    ) {
        db.collection("patient/${patientId}/oneTimeGeneralQuestion").get()
            .addOnSuccessListener { result ->
                val List: MutableList<OnetimeGeneralQuestionVO> = arrayListOf()

                for (document in result) {
                    val hashmap = document.data
                    hashmap?.put("id", document.id)
                    val data = Gson().toJson(hashmap)
                    val dataVo = Gson().fromJson<OnetimeGeneralQuestionVO>(
                        data,
                        OnetimeGeneralQuestionVO::class.java
                    )
                    List.add(dataVo)
                }
                onSuccess(List)
            }
    }

    override fun addAddressToPatient(
        id: String,
        addressVO: AddressVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val addressMap = hashMapOf(
            "newAddress" to addressVO.newAddress,
            "originAddress" to addressVO.originAddress
        )
        db.collection("patient/$id/address")
            .add(addressMap)
            .addOnSuccessListener { Log.d("Success", "Success added") }
            .addOnFailureListener { Log.d("failure", "Failed to add") }

    }


    override fun getPatientFromFirestore(
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
                    val hashmap = document.data
                    hashmap?.put("id", document.id)
                    val data = Gson().toJson(hashmap)
                    val dataVo = Gson().fromJson<PatientVO>(data, PatientVO::class.java)
                    patientList.add(dataVo)
                }
                onSuccess(patientList)
            }
        }
    }

    override fun getGeneralQuestion(
        onSuccess: (generalQuestionVO: List<GeneralQuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("general-question-template").get().addOnSuccessListener { result ->
            val List: MutableList<GeneralQuestionVO> = arrayListOf()

            for (document in result) {
                val hashmap = document.data
                hashmap?.put("id", document.id)
                val data = Gson().toJson(hashmap)
                val dataVo = Gson().fromJson<GeneralQuestionVO>(data, GeneralQuestionVO::class.java)
                List.add(dataVo)
            }
            onSuccess(List)

        }
    }

    override fun getSpecialQuestionBySpecialityName(
        specialityId: String,
        onSuccess: (specialQuestionVO: List<SpecialQuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("specialities/$specialityId/specialQuestion").get()
            .addOnSuccessListener { result ->
                val specialityList: MutableList<SpecialQuestionVO> = arrayListOf()

                for (document in result) {
                    val hashmap = document.data
                    hashmap?.put("id", document.id)
                    val data = Gson().toJson(hashmap)
                    val dataVo =
                        Gson().fromJson<SpecialQuestionVO>(data, SpecialQuestionVO::class.java)
                    specialityList.add(dataVo)
                }
                onSuccess(specialityList)

            }
    }

    override fun getMedicineBySpecialityId(
        specialityId: String,
        onSuccess: (MedicineVO: List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("specialities/$specialityId/medicine").get()
            .addOnSuccessListener { result ->
                val List: MutableList<MedicineVO> = arrayListOf()

                for (document in result) {
                    val hashmap = document.data
                    hashmap?.put("id", document.id)
                    val data = Gson().toJson(hashmap)
                    val dataVo =
                        Gson().fromJson<MedicineVO>(data, MedicineVO::class.java)
                    List.add(dataVo)
                }
                onSuccess(List)

            }
    }
    override fun addDoctors(
        doctorVO: DoctorVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val doctorMap = hashMapOf(
            "name" to doctorVO.name,
            "DOB" to doctorVO.DOB,
            "gender" to doctorVO.gender,
            "experience" to doctorVO.experience,
            "biography" to doctorVO.biography,
            "email" to doctorVO.email,
            "password" to doctorVO.password,
            "phoneNumber" to doctorVO.phoneNumber,
            "specialityName" to doctorVO.specialityName,
            "address" to doctorVO.address,
            "degree" to doctorVO.degree,
            "deviceId" to doctorVO.deviceId,
            "photo" to doctorVO.photo
        )
        db.collection("doctor")
            .document(doctorVO.id)
            .set(doctorMap)
            .addOnSuccessListener { Log.d("Success", "Successfully added to doctor") }
            .addOnFailureListener { Log.d("Failure", "failed to add doctor") }
    }


    override fun getDoctorFromFirestore(
        email: String,
        onSuccess: (doctors: List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("doctor").whereEqualTo("email", email).addSnapshotListener { value, error ->

            error?.let {
                onFailure(it.message ?: "Please Check connection")
            } ?: run {
                val doctorList: MutableList<DoctorVO> = arrayListOf()
                val result = value?.documents ?: arrayListOf()

                for (document in result) {
                    val hashmap = document.data
                    hashmap?.put("id", document.id)
                    val data = Gson().toJson(hashmap)
                    val dataVo = Gson().fromJson<DoctorVO>(data, DoctorVO::class.java)
                    doctorList.add(dataVo)
                    Log.d("SIZE", doctorList.size.toString())
                }
                onSuccess(doctorList)

            }
        }
    }


    override fun broadCastConsultationRequest(
        documentId: String,
        patientVO: PatientVO,
        caseSummaryVO: List<CaseSummaryVO>,
        specialityName: String,
        specialityId: String,
        onSuccess: (id: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        var id = UUID.randomUUID().toString()
        val consultationrequestMap = hashMapOf(
            "id" to id,
            "case-summary" to caseSummaryVO,
            "patient" to patientVO,
            "speciality-name" to specialityName,
            "status" to "new",
            "speciality-id" to specialityId
        )
        id?.let {
            db.collection("consultation_request")
                .document(it)
                .set(consultationrequestMap)
                .addOnSuccessListener {
                    Log.d("success", "Successfully add doctor")
                    onSuccess(id)
                }
                .addOnFailureListener {
                    Log.d("onFailure", "Failed to add doctor")
                    onFailure("Failed to add doctor")
                }
        }
    }


    override fun getConsultationRequestByIdAndAddDoctor(
        id: String,
        onSuccess: (ConsultationRequestVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation_request").whereEqualTo("id", id)
            .addSnapshotListener { value, error ->
                error?.let {

                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val List: MutableList<ConsultationRequestVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()


                    result.isNotEmpty().let {
                        val data = result.first().data?.convertToConsultationRequestVo()
                        data?.let { it1 ->
                            onSuccess(it1)
                        }
                    }

                }
            }
    }

    override fun getBrodaCastConsultationRequest(
        specialityName: String,
        onSuccess: (consultationrequest: List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation_request").whereEqualTo("status","new").whereEqualTo("speciality-name", specialityName).addSnapshotListener { value, error ->

                error?.let {
                    onFailure(it.message ?: "Please Check connection")
                } ?: run {
                    val List: MutableList<ConsultationRequestVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val data = document.data?.convertToConsultationRequestVo()
                        data.let { it?.let { it1 -> List.add(it1) } }
                    }
                    onSuccess(List)

                }
            }
    }


    override fun getCaseSummaryFromRequest(
        requestid: String,
        onSuccess: (caseSummary: List<CaseSummaryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation_request/${requestid}/case-summary")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please Check connection")
                } ?: run {
                    val patientList: MutableList<CaseSummaryVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id)
                        val data = Gson().toJson(hashmap)
                        val dataVo = Gson().fromJson<CaseSummaryVO>(data, CaseSummaryVO::class.java)
                        patientList.add(dataVo)
                    }
                    onSuccess(patientList)
                }
            }
    }

    override fun getPatientFromRequest(
        requestid: String,
        onSuccess: (PatientVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation_request/${requestid}/patient")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please Check connection")
                } ?: run {
//                val patientList: MutableList<PatientVO> = arrayListOf()
//                val result = value?.documents ?: arrayListOf()
//
//                for (document in result) {
//                    val hashmap = document.data
//                    hashmap?.put("id", document.id)
//                    val data = Gson().toJson(hashmap)
//                    val dataVo = Gson().fromJson<PatientVO>(data, PatientVO::class.java)
//                    patientList.add(dataVo)

                    val List: MutableList<PatientVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()


                    result.isNotEmpty().let {
                        val data = result.first().data?.convertToPatientVo()
                        data?.let { it1 -> onSuccess(it1) }
                    }

                }
            }
    }


    override fun getCaseSummaryFromConsultation(
        requestid: String,
        onSuccess: (caseSummary: List<CaseSummaryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation/${requestid}/case-summary")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please Check connection")
                } ?: run {
                    val patientList: MutableList<CaseSummaryVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id)
                        val data = Gson().toJson(hashmap)
                        val dataVo = Gson().fromJson<CaseSummaryVO>(data, CaseSummaryVO::class.java)
                        patientList.add(dataVo)
                    }
                    onSuccess(patientList)
                }
            }
    }

    override fun getPatientFromConsultation(
        requestid: String,
        onSuccess: (PatientVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation/${requestid}/patient").addSnapshotListener { value, error ->
            error?.let {
                onFailure(it.message ?: "Please Check connection")
            } ?: run {
//                val patientList: MutableList<PatientVO> = arrayListOf()
//                val result = value?.documents ?: arrayListOf()
//
//                for (document in result) {
//                    val hashmap = document.data
//                    hashmap?.put("id", document.id)
//                    val data = Gson().toJson(hashmap)
//                    val dataVo = Gson().fromJson<PatientVO>(data, PatientVO::class.java)
//                    patientList.add(dataVo)

                val List: MutableList<PatientVO> = arrayListOf()

                val result = value?.documents ?: arrayListOf()


                result.isNotEmpty().let {
                    val data = result.first().data?.convertToPatientVo()
                    data?.let { it1 -> onSuccess(it1) }
                }

            }
        }
    }

    override fun getConsultationByDoctor(
        id: String,
        onSuccess: (consultation: List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation").addSnapshotListener { value, error ->
            error?.let {
                onFailure(it.message ?: "Please check connection")
            } ?: run {
                val List: MutableList<ConsultationVO> = arrayListOf()
                val result = value?.documents ?: arrayListOf()
                for (document in result) {
                    val consultation = document.data?.convertToConsultationVO()
                    consultation?.let { List.add(it) }
                }
                onSuccess(List)
            }
        }
    }

    override fun getConsultationByPatient(
        id: String,
        onSuccess: (consultation: List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation/${id}/patient")
            .addSnapshotListener { value, error ->
                error?.let {

                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val List: MutableList<ConsultationVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val consultation = document.data?.convertToConsultationVO()
                        consultation?.let { List.add(it) }
                    }
                    onSuccess(List)
                }
            }
    }

    override fun getConfirmConsultationRequest(
        id: String,
        onSuccess: (ConsultationRequestVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation_request").whereEqualTo("id", id).whereEqualTo("status","new")
            .addSnapshotListener { value, error ->

                error?.let { onFailure(it.message ?: "Please Check Connection") } ?: run {
                    val request: ConsultationRequestVO = ConsultationRequestVO()
                    if (value?.documents?.isNotEmpty()!!) {
                        val result = value.documents.first()

                        val data = result?.data?.convertToConsultationRequestVo()
                        data?.let {
                            onSuccess(it) }
                    }
                }
            }
    }

        override fun startConsultation(
        caseSummaryVO: List<CaseSummaryVO>,
        id: String,
        dataTime: String,
        patientVO: PatientVO,
        doctorVO: DoctorVO,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {

        val ref = db.collection("consultation_request").document("${id}")

        db.runTransaction { transaction ->
            val snapShot = transaction.get(ref)

            transaction.update(ref, "status", "accept")
            transaction.update(ref, "id", id)
            transaction.update(ref, "doctor", doctorVO)
        }.addOnSuccessListener { result ->
            Log.d("Status Update", "Transaction success: $result")
        }.addOnFailureListener { e ->
            Log.w("Status Update", "Transaction failure", e)
        }
        val consultationMap = hashMapOf(
            "id" to id,
            "status" to "start",
            "speciality" to doctorVO.specialityName,
            "create-at" to dataTime,
            "patient" to patientVO,
            "doctor" to doctorVO,
            "case-summary" to caseSummaryVO
        )
        db.collection("consultation")
            .document(id)
            .set(consultationMap)
            .addOnSuccessListener {
                Log.d("Success", "Success added to consultation")
                onSuccess(id)
            }
            .addOnFailureListener {
                Log.d("Failed", "Failed added to consultation")
            }

    }


    override fun getMessageChart(
        documentId: String,
        onSuccess: (messageVO: List<ChatMessageVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation/${documentId}/chat-message")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please Check connection")
                } ?: run {
                    val chatList: MutableList<ChatMessageVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val chatMessage = document.data?.convertToChatVO()
                        chatMessage?.let {
                            chatList.add(chatMessage)
                        } ?: Log.d("No Chat", "No Chat List")
                    }
                    onSuccess(chatList)
                }
            }
    }

    override fun addToPrescription(
        documentId: String,
        id: String,
        mname: String,
        price: String,
        routineVO: RoutineVO
    ) {////////////
        val prescriptionMap = hashMapOf(
            "id" to id,
            "mname" to mname,
            "price" to price,
            "routine" to routineVO
        )
        db.collection("consultation/${documentId}/prescription")
            .document(UUID.randomUUID().toString())
            .set(prescriptionMap)
            .addOnSuccessListener {
                Log.d(
                    "Success",
                    "Successfully preseciption added to prescription"
                )
            }
            .addOnFailureListener {
                Log.d(
                    "Failure",
                    "failed to add prescription added to prescription"
                )
            }
    }

    override fun sendMessage(
        id: String,
        message: ChatMessageVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val messageMap = hashMapOf(
            "text-message" to message.textMessage,
            "image-message" to message.imagemessage,
            "send-by" to message.sendBy,
            "send-at" to message.sendAt,
            "id" to message.id
        )

        db.collection("consultation")
            .document(id)
            .collection("chat-message")
            .document(message.id!!)
            .set(messageMap)
            .addOnSuccessListener {
                Log.d("success", "Successfully add messages")
                onSuccess()
            }
            .addOnFailureListener {
                Log.d("onFailure", "Failed to add messages")
                onFailure("Failed to add messages")
            }
    }

    override fun getAllMedicine(
        specialityId: String,
        onSuccess: (medicineVO: List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("speciality/${specialityId}/medicine")
            .get()
            .addOnSuccessListener { result ->
                val medicineList: MutableList<MedicineVO> = arrayListOf()
                for (document in result) {
                    val hashMap = document.data
                    hashMap?.put("id", document.id)
                    val data = Gson().toJson(hashMap)
                    val dataVo = Gson().fromJson<MedicineVO>(data, MedicineVO::class.java)
                    medicineList.add(dataVo)
                }
                onSuccess(medicineList)
            }
    }

    override fun getMedicinetoPrescribe(
        specialityVO: SpecialityVO,
        specialityName: String,
        onSuccess: (medicine: MedicineVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("specialities/${specialityVO.id}/medicine")
            .whereEqualTo(specialityVO.specialityName, specialityName)
            .get()
            .addOnSuccessListener { result ->
                val medicineList: MutableList<MedicineVO> = arrayListOf()

                for (document in result) {
                    val hashmap = document.data
                    hashmap?.put("id", document.id)
                    val medicine = MedicineVO()
                    medicine.id = hashmap["id"] as String
                    medicine.mname = hashmap["mname"] as String
                    medicine.price = hashmap["price"] as String
                    medicineList.add(medicine)
                }

            }
    }

    override fun getConsultation(
        onSuccess: (consultation: List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation").addSnapshotListener { value, error ->
            error?.let {
                onFailure(it.message ?: "Please check connection")
            } ?: run {
                val consultationList: MutableList<ConsultationVO> = arrayListOf()
                val result = value?.documents ?: arrayListOf()
                for (document in result) {
                    val consultation = document.data?.convertToConsultationVO()
                    consultation?.let {
                        consultationList.add(consultation)
                    }
                        ?: Log.d("Error", "No consultation data")
                }
                onSuccess(consultationList)
            }
        }
    }

    override fun getPrescription(
        documentId: String,
        onSuccess: (prescription: List<PresriptionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation/${documentId}/prescription")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please Check connection")
                } ?: run {
                    val presriptionList: MutableList<PresriptionVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val prescription = document.data.convertToPrescriptionVO()
                        presriptionList.add(prescription)
                    }
                    onSuccess(presriptionList)
                }
            }
    }

    override fun addedRecentlyDoctor(
        id: String,
        patientVO: PatientVO,
        doctorVo: DoctorVO,
        onSuccess: (patientVO: PatientVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val recentlyDoctorMap = hashMapOf(
            "id" to doctorVo.id,
            "name" to doctorVo.name,
            "speciality-name" to doctorVo.specialityName
        )
        db.collection("patient/${id}/recently-doctor")
            .add(recentlyDoctorMap)
            .addOnSuccessListener {
                Log.d(
                    "Success",
                    "Successfully added to recently doctor"
                )
            }
            .addOnFailureListener { Log.d("failed", "failed to add to recently doctor") }
    }

    override fun getRecentlyDoctor(
        id: String,
        onSuccess: (recentlyDoctor: List<RecentlyDoctorVo>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("patient/$id/recently-doctor").addSnapshotListener { value, error ->
            error?.let {
                onFailure(it.message ?: "Please Check connection")
            } ?: run {
                val recentlyDoctorList: MutableList<RecentlyDoctorVo> = arrayListOf()
                val result = value?.documents ?: arrayListOf()

                for (document in result) {
                    val recentlyDoctor = document.data.convertToRecentlyDoctorVO()
                    recentlyDoctorList.add(recentlyDoctor)
                }
                onSuccess(recentlyDoctorList)
            }
        }
    }

    override fun sendDirectRequest(
        patientVO: PatientVO,
        caseSummaryVO: CaseSummaryVO,
        recentlyDoctor: RecentlyDoctorVo,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val consultationrequestMap = hashMapOf(
            "case-summary" to caseSummaryVO,
            "patient" to patientVO,
            "speciality-name" to recentlyDoctor.specialityName
        )
        db.collection("consultation-request")
            .add(consultationrequestMap)
            .addOnSuccessListener {
                Log.d(
                    "Success",
                    "Successfully added to consultation request"
                )
            }
            .addOnFailureListener { Log.d("Failure", "failed to add consultation request") }
    }


    override fun finishConsultation(
        consultation: ConsultationVO,
        documentId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
//        val finishMap = hashMapOf(
//            "finish-flag" to true
//        )
//        db.collection("consultation")
//            .document("${documentId}")
//            .set(finishMap)
//            .addOnFailureListener { Log.d("Success", "Successfully finished") }
//            .addOnFailureListener { Log.d("Failed", "Failed to finished") }
    }


    override fun checkOutMedicine(
        prescription: List<PresriptionVO>,
        deliveryRoutineVO: DeliveryRoutineVO,
        id: String,
        address: String,
        onSuccess: (checkOutVO: CheckOutVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val checkOutMap = hashMapOf(
            "id" to id,
            "delivery-routine" to deliveryRoutineVO,
            "prescription" to prescription,
            "address" to address
        )

        db.collection("checkout")
            .document(UUID.randomUUID().toString())
            .set(checkOutMap)
            .addOnFailureListener { Log.d("Success", "Successfully added checkout") }
            .addOnFailureListener { Log.d("Failed", "Failed to add checkout") }

    }

}
