package com.padc.doctor.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.padc.doctor.delegates.ChatDelegate
import com.padc.doctor.mvp.views.ChatView
import com.padc.shared.data.vos.ChatMessageVO
import com.padc.shared.data.vos.MedicineVO
import com.padc.shared.data.vos.SpecialQuestionVO
import com.padc.shared.mvp.presenters.BasePresenter
import java.text.SimpleDateFormat
import java.util.*

interface ChatPresenter : BasePresenter<ChatView> ,ChatDelegate{
    fun onUiReady(id: String,name : String,sid : String,lifecycleOwner: LifecycleOwner)

    fun onTapMedicineButton(specialityName: String,id: String)
    fun onTapQuestionButton(specialityName: String,id: String)

    fun onTapSendIcon(id : String, text : String, image : String){
    }
}