package com.padc.the_health_care_app.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.ChatMessageVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter
import com.padc.the_health_care_app.mvp.presenters.PatientChatPresenter
import com.padc.the_health_care_app.mvp.views.PatientChatView
import com.padc.the_health_care_app.utils.SessionManager
import java.text.SimpleDateFormat
import java.util.*

class PatientChatPresenterImpl :PatientChatPresenter, AbstractBasePresenter<PatientChatView>(){
    private var mModel : HealthCareModel = HealthCareModelImpl
    override fun onTapSend(id: String, text: String, image: String) {
        val dateformat = SimpleDateFormat(" HH:mm a")
        val currentDatetime : String = dateformat.format(Date())
        val messageVO = ChatMessageVO(
            id = UUID.randomUUID().toString(),
            textMessage =  text,
            imagemessage = image,
            sendBy = SessionManager.patient_id.toString(),
            sendAt = currentDatetime
        )
        mModel.sendMessageBySender(id,messageVO,onSuccess = {},onFailure = {})
    }

    override fun onUiReady(id: String, lifecycleOwner: LifecycleOwner) {
       mModel.getConsultationById(id,onSuccess = {consultation ->
             mView?.showSpeciality(consultation.spciality.toString())
         //  mView?.showSendMessageLayout()

           mView?.displayPatientQuestion(consultation.caseSummaryVO)
           consultation.patient?.let { mView?.displayPatientInfo(it) }
       },onFailure = {})

        mModel.getPrescription(id,onSuccess = {
            mView?.showPrescription(
                it
            )
        },onFailure = {})


        mModel.getAllChatMessage(id,onSuccess = {
            mView?.displayPatientChat(it)
        },onFailure = {})

    }

//    override fun onTapCheckOut(id: String) {
//        mView?.navigateToOrderPrescription()
//    }

    override fun onTapOrderPrescription() {
        mView?.navigateToOrderPrescription()
    }

}