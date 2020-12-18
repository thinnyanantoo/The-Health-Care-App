package com.padc.the_health_care_app.mvp.presenters.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.data.vos.PatientVO
import com.padc.shared.data.vos.SpecialityVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter
import com.padc.the_health_care_app.mvp.presenters.SpecialityPresenter
import com.padc.the_health_care_app.mvp.views.SpecialityView
import com.padc.the_health_care_app.utils.SessionManager

class SpecialityPresenterImpl : SpecialityPresenter, AbstractBasePresenter<SpecialityView>() {
    var mModel: HealthCareModel = HealthCareModelImpl
    override fun onUiReady(lifeCycleOwner: LifecycleOwner,patientId : String) {
        requestSpecialityData(lifeCycleOwner)
        confrimConsultation(lifeCycleOwner)

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

    override fun onTapStartConsultation(
        consultationId: String,
        consultationRequestVO: ConsultationRequestVO
    ) {
        Log.d("consultationId",consultationId)
        mView?.navigateToChartActivity(consultationId,consultationRequestVO)
    }


    private fun requestSpecialityData(lifeCycleOwner: LifecycleOwner) {
        mModel.getSpecialities(
            onError = {
                mView?.showError(it)
            }).observe(lifeCycleOwner, Observer {
            mView?.displaySpecialityList(it)
        })
    }

    private fun confrimConsultation(lifeCycleOwner: LifecycleOwner){
        mModel.getConsultationConfirmByPatient(SessionManager.request_id.toString(),onSuccess = {consultationRequest->
            consultationRequest.doctorVO?.let {
                mView?.showConsultationRequestReceived(consultationRequest)
            }
        },
        onFailure = {
            Log.e("ERROR FOR DOCTOR","NO DOCTOR")
        })
    }
}