package com.padc.the_health_care_app.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padc.shared.data.vos.PatientVO

interface SignInPresenter {
    fun onUiReady(lifecycleOwner: LifecycleOwner)
    fun onTapLogin(email : String, password : String,lifecycleOwner: LifecycleOwner)
    fun onTapRegister()
}