package com.padc.doctor.mvp.presenter.impls

import android.text.format.DateUtils
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padc.doctor.mvp.presenter.ShowSpecialQuestionPresenter
import com.padc.doctor.mvp.views.ShowSpecialQuestionView
import com.padc.doctor.utils.SessionManager
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.ChatMessageVO
import com.padc.shared.data.vos.SpecialityVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter
import java.text.SimpleDateFormat
import java.util.*

class ShowSpecialQuestionPresenterImpl : ShowSpecialQuestionPresenter, AbstractBasePresenter<ShowSpecialQuestionView> () {
    var mModel : HealthCareModel = HealthCareModelImpl
    var conId = ""
    override fun onUiReady(speicalityName: String,speicalityId : String,consultId: String,lifecycleOwner: LifecycleOwner) {
        conId = consultId
        mModel.getSpecialQuestionBySpecialityNameFromFirebaseAndSaveToDatabase(speicalityId,{},{})
        mModel.getSpecialQuestionBySpecialityNameFromDatabase().observe(lifecycleOwner, Observer {
            mView?.displaySpecialQuestion(it)
        })
    }

    override fun onTapQuestionItem(question : String) {
        val dateformat = SimpleDateFormat(" HH:mm a")
        val currentDatetime : String = dateformat.format(Date())
        val messageVO = ChatMessageVO(
            id = UUID.randomUUID().toString(),
            textMessage =  question,
            imagemessage = "image",
            sendBy = SessionManager.doctor_id.toString(),
            sendAt = currentDatetime
        )
        mModel.sendMessageBySender(SessionManager.con_id.toString(),messageVO,onSuccess = {},onFailure = {})
      //  mModel.sendMessageBySender(id,messageVO,onSuccess = {},onFailure = {})
        mView?.navigateToChat()

    }

}

