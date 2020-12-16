package com.padc.the_health_care_app.mvp.presenters

import androidx.lifecycle.LifecycleOwner

interface MainPresenter {
    fun onUiReady(lifecycleOwner: LifecycleOwner,patientId : String)
}