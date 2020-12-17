package com.padc.doctor.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.padc.doctor.mvp.views.ShowPatientInfoView
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.data.vos.PatientVO
import com.padc.shared.mvp.presenters.BasePresenter

interface ShowPatientInfoPresenter : BasePresenter<ShowPatientInfoView>{
    fun onUiReady(requestId : String,specialityName : String, specialityId : String,lifecycleOwner: LifecycleOwner)
    fun onTapStartConsultation(consultationRequestVO: ConsultationRequestVO)
}