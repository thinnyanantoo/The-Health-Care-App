package com.padc.doctor.mvp.presenter.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padc.doctor.mvp.presenter.ChatPresenter
import com.padc.doctor.mvp.views.ChatView
import com.padc.doctor.utils.SessionManager
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.ChatMessageVO
import com.padc.shared.data.vos.DoctorVO
import com.padc.shared.data.vos.MedicineVO
import com.padc.shared.data.vos.SpecialQuestionVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter
import java.text.SimpleDateFormat
import java.util.*

class ChatPresenterImpl : ChatPresenter, AbstractBasePresenter<ChatView>() {
    private var mModel: HealthCareModel = HealthCareModelImpl


        //Log.d("IDDDDDD",id)

//        mModel.getConsultationFromDB(id).observe(lifecycleOwner, Observer {
//            data ->
//            data?.let{
//                mView?.showPatientInfo(data)
//            }
//        })


//        mModel.getPatientFromRequest(id,onSuccess = {
//            mView?.showPatientInfo(it)
//        },onFailure = {})
//
//        mModel.getCaseSummaryFromRequst(id,onSuccess = {
//            mView?.showSpecialQuestionAnswer(it)
//        },onFailure = {})

//        mModel.getPatientFromConsultation(id,onSuccess = {
//            mView?.showPatientInfo(it)
//        },onFailure = {})
//
//        mModel.getCaseSummaryFromConsultation(id,onSuccess = {
//            mView?.showSpecialQuestionAnswer(it)
//        },onFailure = {})


        override fun onUiReady(id: String, name: String, sid: String, lifecycleOwner: LifecycleOwner) {
//        mModel.getPatientFromRequest(id,onSuccess = {
//            mView?.showPatientInfo(it)
//        },onFailure = {})
//
//        mModel.getCaseSummaryFromRequst(id,onSuccess = {
//            mView?.showSpecialQuestionAnswer(it)
//        },onFailure = {})


//            mModel.getRequestCaseSummaryById(id).observe(lifecycleOwner, Observer {
//                it?.let {
//                    mView?.showPatientInfo(it)
//                    it.caseSummaryVO?.toMutableList()?.let { it1 -> mView?.showSpecialQuestionAnswer(it1) }}
//            })

            mModel.getPrescription(consultationId = SessionManager.con_id.toString(),onSuccess = {
                Log.d("SIze",it.size.toString())
                mView?.displayPrescription(
                    it

                )
            },onFailure = {
                it
            })

            mModel.getConsultationById(id,onSuccess = {consultation ->
//                mView?.showSpeciality(consultation.spciality.toString())
                //  mView?.showSendMessageLayout()
                mView?.showSpecialQuestionAnswer(consultation.caseSummaryVO)
                consultation.patient?.let { mView?.showPatientInfo(it) }
            },onFailure = {})


            mModel.getAllChatMessage(SessionManager.con_id.toString(), onSuccess = {
                mView?.displayPatientChat(it)
            },onFailure = {})


        }



        override fun onTapMedicineButton(specialityName: String, id: String,consultId: String) {
        mView?.navigateToMedicineActivity(specialityName, id,consultId)
    }

    override fun onTapQuestionButton(specialityName: String, id: String,consultId: String) {
        mView?.navigateToQuestionActivity(specialityName, id,consultId =consultId )
    }

    override fun onTapSendIcon(id: String, text: String, image: String) {
        SessionManager.con_id = id
        val dateformat = SimpleDateFormat(" HH:mm a")
        val currentDatetime : String = dateformat.format(Date())
        val messageVO = ChatMessageVO(
            id = UUID.randomUUID().toString(),
            textMessage =  text,
            imagemessage = image,
            sendBy = SessionManager.doctor_id.toString(),
            sendAt = currentDatetime
        )
        mModel.sendMessageBySender(id,messageVO,onSuccess = {},onFailure = {})
    }

    override fun onTapChatToCheckOut() {
        TODO("Not yet implemented")
    }


}