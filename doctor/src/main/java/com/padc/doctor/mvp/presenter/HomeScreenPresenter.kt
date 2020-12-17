package com.padc.doctor.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.padc.doctor.delegates.RequestDelegate
import com.padc.doctor.mvp.views.HomeScreenView
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.mvp.presenters.BasePresenter

interface HomeScreenPresenter: BasePresenter<HomeScreenView>,RequestDelegate{
    fun onUiReady(specialityName : String,lifecycleOwner: LifecycleOwner)

}