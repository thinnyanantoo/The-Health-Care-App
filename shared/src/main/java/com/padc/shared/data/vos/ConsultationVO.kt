package com.padc.shared.data.vos

import androidx.room.Entity
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class ConsultationVO(
    var doctor: DoctorVO = DoctorVO(),
    var patient: PatientVO = PatientVO(),
    var id: String = "",
    var chatMessage: ChatMessageVO = ChatMessageVO(),
    var presription: PresriptionVO = PresriptionVO()
)

fun MutableMap<String,Any?> . convertToConsultationVO(): ConsultationVO{
    val consultation = ConsultationVO()
    consultation.doctor = this.get("doctor") as DoctorVO
    consultation.patient = this.get("patient")as PatientVO
    consultation.id = this.get("id")as String
    consultation.chatMessage = this.get("chat-message")as ChatMessageVO
    consultation.presription = this.get("prescription")as PresriptionVO
    return consultation
 }
