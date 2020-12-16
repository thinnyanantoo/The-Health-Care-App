package com.padc.doctor.mvp.presenter.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.padc.doctor.mvp.presenter.HomeScreenPresenter
import com.padc.doctor.mvp.views.HomeScreenView
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter

class HomeScreenPresenterImpl : HomeScreenPresenter, AbstractBasePresenter<HomeScreenView>() {
    private var mModel : HealthCareModel = HealthCareModelImpl
    override fun onUiReady(specialityName : String, lifecycleOwner: LifecycleOwner) {
            mModel.getBroadCastConsultationRequest(specialityName,onSuccess = {
                Log.d("SSS",it.size.toString())
                mView?.showConsultationRequest(it)
            },onFailure = {
                Log.e("Error","no consultation request")
            })
        }


    override fun onTapAccept() {
        TODO("Not yet implemented")
    }

    override fun onTapDecline() {
        TODO("Not yet implemented")
    }

}