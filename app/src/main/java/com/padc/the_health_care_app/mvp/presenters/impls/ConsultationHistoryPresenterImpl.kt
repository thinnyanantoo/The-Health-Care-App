package com.padc.the_health_care_app.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.ConsultationVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter
import com.padc.the_health_care_app.mvp.presenters.ConsultationHistoryPresenter
import com.padc.the_health_care_app.mvp.views.ConsultationHistoryView

//class ConsultationHistoryPresenterImpl : ConsultationHistoryPresenter,AbstractBasePresenter<ConsultationHistoryView>(){
//    private val mModel : HealthCareModel = HealthCareModelImpl
//    override fun onUiReady(consultation: ConsultationVO,lifecycleOwner: LifecycleOwner) {
//        mModel.getConsultation(onSuccess = {
//            mView?.showConsultatioHistory(consultation)
//        },onError = {
//
//        })
//    }
//
//}