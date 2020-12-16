package com.padc.doctor.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.data.vos.PatientVO

interface ShowPatientInfoPresenter {
    fun onUiReady(specialityName : String, lifecycleOwner: LifecycleOwner)
}