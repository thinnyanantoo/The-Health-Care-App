package com.padc.shared.data.vos

import androidx.room.Entity
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class ConsultationVO(
    var doctor: DoctorVO? = null,
    var patient: PatientVO ? = null,
    var id: String = "",
    var chatMessage: ChatMessageVO? = null,
    var caseSummaryVO: ArrayList<CaseSummaryVO> = arrayListOf(),
    var prescription : List<MedicineVO> = arrayListOf(),
    var status : String? = "",
    var crateAt : String? = null,
    var spciality : String ? = null

    // var presription: PresriptionVO = PresriptionVO()
)

fun MutableMap<String,Any?> . convertToConsultationVO(): ConsultationVO{
    val consultation = ConsultationVO()
    consultation.doctor = toConvertDoctor((this?.get("doctor") as HashMap<String, String>))
    consultation.patient = toConvertPatient((this?.get("patient")as HashMap<String, String>))
    consultation.status = this.get("status")as String
    val que : ArrayList<CaseSummaryVO>? = arrayListOf()
    val value = this?.get("case-summary") as ArrayList<HashMap<String,Any>>?
    value?.forEach{
        it.convertToCaseSummary()?.let { it1 -> que?.add (it1) }
    }
    consultation.caseSummaryVO = que!!
    consultation.id = this.get("id")as String
    consultation.chatMessage = toConverMessage((this?.get("chat-message")as HashMap<String,String>?))    //  consultation.presription = this.get("prescription")as PresriptionVO
    return consultation
}

fun MutableMap<String,Any>?.convertToCaseSummary(): CaseSummaryVO?{
    val question = CaseSummaryVO()
    question.question = this?.get("question")as String
    question.answer = this?.get("answer")as String
    question.id = this?.get("id")as String
    return question
}

fun toConverMessage(data : HashMap<String, String>?) : ChatMessageVO? {
data?.let {
    val message = ChatMessageVO()
    message.sendAt = data?.get("send-at") as String
    message.imagemessage = data.get("message") as String
    message.photo = data.get("photo") as String
    message.textMessage = data.get("message") as String
    message.time = data.get("time") as String
    message.sendBy = data.get("send-by") as String
    return message
}
    return null


}


