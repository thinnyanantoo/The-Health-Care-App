package com.padc.shared.data.vos

import androidx.room.Entity
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class ConsultationVO(
    var doctor: DoctorVO? = null,
    var patient: PatientVO ? = null,
    var id: String = "",
    var chatMessage: ChatMessageVO? = null
   // var presription: PresriptionVO = PresriptionVO()
)

fun MutableMap<String,Any?> . convertToConsultationVO(): ConsultationVO{
    val consultation = ConsultationVO()
    consultation.doctor = toConvertDoctor((this?.get("doctor") as HashMap<String, String>))
    consultation.patient = toConvertPatient((this?.get("patient")as HashMap<String, String>))
    consultation.id = this.get("id")as String
    consultation.chatMessage = toConverMessage((this?.get("chat-message")as HashMap<String,String>))
  //  consultation.presription = this.get("prescription")as PresriptionVO
    return consultation
 }

fun toConverMessage(data : HashMap<String, String>?) : ChatMessageVO? {
    data?.let{
        val message = ChatMessageVO()
        message.sendAt = data.get("send-at")as String
        message.imagemessage = data.get("message") as String
        message.photo = data.get("photo")as String
        message.textMessage = data.get("message")as String
        message.time = data.get("time")as String
        message.sendBy = data.get("send-by")as String
        return message
    }
      return null
}
