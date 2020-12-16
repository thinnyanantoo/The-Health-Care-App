package com.padc.doctor.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.padc.doctor.mvp.views.RegisterView
import com.padc.shared.mvp.presenters.BasePresenter

interface RegisterPresenter : BasePresenter<RegisterView> {
    fun onTapRegister(
        email: String,
        name: String,
        password: String,
        phoneNumber: String,
        speciality: String,
        degree: String,
        biography : String,
        experience : String,
        DOB : String,
        photo: String,
        address: String)

    fun onUiReady(lifecycleOwner: LifecycleOwner)
}