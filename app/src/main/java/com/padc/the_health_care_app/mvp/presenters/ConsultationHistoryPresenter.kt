package com.padc.the_health_care_app.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padc.shared.data.vos.ConsultationVO
import com.padc.shared.mvp.presenters.BasePresenter
import com.padc.the_health_care_app.mvp.views.PatientChatView
import com.padc.the_health_care_app.viewpods.RecommendViewPodForPatient

interface ConsultationHistoryPresenter : BasePresenter<PatientChatView>{
    fun onUiReady(consultation : ConsultationVO,lifecycleOwner: LifecycleOwner)
}