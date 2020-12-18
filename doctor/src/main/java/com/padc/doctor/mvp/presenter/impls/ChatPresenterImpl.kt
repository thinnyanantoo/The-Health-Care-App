package com.padc.doctor.mvp.presenter.impls

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
    var documentId = ""

    override fun onUiReady(id: String, name: String, sid: String, lifecycleOwner: LifecycleOwner) {
     //   mModel.getBroadCastConsultationRequestFromFireStoreAndSaveToDatabase(name,{
    //    },{})
        mModel.getRequestCaseSummaryById(id).observe(lifecycleOwner, Observer {
            it.let {
                mView?.showPatientInfo(it)

            it.caseSummaryVO?.toMutableList()?.let { it1 -> mView?.showSpecialQuestionAnswer(it1) }}
        })


        mModel.getAllChatMessage(id, onSuccess = {
            mView?.displayPatientChat(it)
        },onFailure = {})


    }

    override fun onTapMedicineButton(specialityName: String, id: String) {
        mView?.navigateToMedicineActivity(specialityName, id)
    }

    override fun onTapQuestionButton(specialityName: String, id: String) {
        mView?.navigateToQuestionActivity(specialityName, id)
    }

    override fun onTapSendIcon(id: String, text: String, image: String) {
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