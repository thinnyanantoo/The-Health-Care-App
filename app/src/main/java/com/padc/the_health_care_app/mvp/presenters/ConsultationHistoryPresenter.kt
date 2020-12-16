package com.padc.the_health_care_app.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padc.shared.data.vos.ConsultationVO

interface ConsultationHistoryPresenter {
    fun onUiReady(consultation : ConsultationVO,lifecycleOwner: LifecycleOwner)
}