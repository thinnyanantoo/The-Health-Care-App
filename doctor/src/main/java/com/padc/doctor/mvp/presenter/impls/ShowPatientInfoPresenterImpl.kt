package com.padc.doctor.mvp.presenter.impls

import androidx.lifecycle.LifecycleOwner
import com.padc.doctor.mvp.presenter.ShowPatientInfoPresenter
import com.padc.doctor.mvp.views.ShowPatientInfoView
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.data.vos.PatientVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter

class ShowPatientInfoPresenterImpl : ShowPatientInfoPresenter, AbstractBasePresenter<ShowPatientInfoView>() {
    private val mModel : HealthCareModel = HealthCareModelImpl
    override fun onUiReady(specialityName : String,lifecycleOwner: LifecycleOwner) {
      mModel.getBroadCastConsultationRequest(specialityName,onSuccess = {
         // mView?.showPatientInfoFromConsultationRequest(it)
      },onFailure = {

      })
    }
}