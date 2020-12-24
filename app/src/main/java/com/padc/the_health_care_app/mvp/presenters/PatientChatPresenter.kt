package com.padc.the_health_care_app.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padc.shared.mvp.presenters.BasePresenter
import com.padc.the_health_care_app.delegates.ChatDelegate
import com.padc.the_health_care_app.mvp.views.PatientChatView
import com.padc.the_health_care_app.viewpods.RecommendViewPodForPatient

interface PatientChatPresenter : BasePresenter<PatientChatView>,RecommendViewPodForPatient.Delegate{
    fun onTapSend(id : String, text : String, image : String)

    fun onUiReady(id:String, lifecycleOwner: LifecycleOwner)

   // fun onTapCheckOut(id: String)
}