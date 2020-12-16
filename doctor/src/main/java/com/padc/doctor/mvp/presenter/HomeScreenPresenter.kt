package com.padc.doctor.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.padc.doctor.mvp.views.HomeScreenView
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.mvp.presenters.BasePresenter

interface HomeScreenPresenter: BasePresenter<HomeScreenView>{
    fun onUiReady(specialityName : String,lifecycleOwner: LifecycleOwner)
    fun onTapAccept()
    fun onTapDecline()

}