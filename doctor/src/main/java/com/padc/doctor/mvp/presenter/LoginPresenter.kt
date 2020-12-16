package com.padc.doctor.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.padc.doctor.mvp.views.LoginView
import com.padc.shared.mvp.presenters.BasePresenter

interface LoginPresenter : BasePresenter<LoginView> {
    fun onTapLogin(email : String,password : String,lifecycleOwner: LifecycleOwner)
    fun onTapRegister()
    fun onUiReady(lifecycleOwner: LifecycleOwner)
}