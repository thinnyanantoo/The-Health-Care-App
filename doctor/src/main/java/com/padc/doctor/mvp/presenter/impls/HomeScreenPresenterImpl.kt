package com.padc.doctor.mvp.presenter.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padc.doctor.mvp.presenter.HomeScreenPresenter
import com.padc.doctor.mvp.views.HomeScreenView
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter

class HomeScreenPresenterImpl : HomeScreenPresenter, AbstractBasePresenter<HomeScreenView>() {
    private var mModel : HealthCareModel = HealthCareModelImpl
    override fun onUiReady(specialityName : String, lifecycleOwner: LifecycleOwner) {

        mModel.getBroadCastConsultationRequestFromFireStoreAndSaveToDatabase(specialityName,{},{})
            mModel.getRequestFromDatabase(onError = {}).observe(
                lifecycleOwner, Observer {
                    mView?.showConsultationRequest(it)

                }
            )
        }


    override fun onTapButtonAcceptInRequest(consultationRequestVO: ConsultationRequestVO) {
        mView?.navigateToPatientCaseSummaryInfo(consultationRequestVO)
    }

    override fun onTapButtonDeclineInRequest() {
    }

}