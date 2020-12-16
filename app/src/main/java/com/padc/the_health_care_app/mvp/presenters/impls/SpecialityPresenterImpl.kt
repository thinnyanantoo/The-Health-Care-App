package com.padc.the_health_care_app.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.PatientVO
import com.padc.shared.data.vos.SpecialityVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter
import com.padc.the_health_care_app.mvp.presenters.SpecialityPresenter
import com.padc.the_health_care_app.mvp.views.SpecialityView

class SpecialityPresenterImpl : SpecialityPresenter, AbstractBasePresenter<SpecialityView>() {
    var mModel: HealthCareModel = HealthCareModelImpl
    override fun onUiReady(lifeCycleOwner: LifecycleOwner,patientId : String) {
        requestSpecialityData(lifeCycleOwner)

    }

    override fun onTapCancelInDialog(lifeCycleOwner: LifecycleOwner) {
       requestSpecialityData(lifeCycleOwner = lifeCycleOwner)
    }

    override fun onTapSureInDialog(specialityVO: SpecialityVO,patientId: String) {
        mView?.displayFormToFillPatientInfo(specialityVO,patientId)
    }

    override fun onTapSpecialityItem(specialityVO: SpecialityVO) {
        mView?.showDialog(specialityVO)
    }

    private fun requestSpecialityData(lifeCycleOwner: LifecycleOwner) {
        mModel.getSpecialities(
            onError = {
                mView?.showError(it)
            }).observe(lifeCycleOwner, Observer {
            mView?.displaySpecialityList(it)
        })
    }
}