package com.padc.doctor.mvp.presenter.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padc.doctor.mvp.presenter.ShowPatientInfoPresenter
import com.padc.doctor.mvp.views.ShowPatientInfoView
import com.padc.doctor.utils.SessionManager
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.data.vos.PatientVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter

class ShowPatientInfoPresenterImpl : ShowPatientInfoPresenter, AbstractBasePresenter<ShowPatientInfoView>() {
    private val mModel: HealthCareModel = HealthCareModelImpl
    var rId = ""
    override fun onUiReady(requestId : String,specialityName: String, specialityId : String,lifecycleOwner: LifecycleOwner) {
        rId = requestId
       // mModel.getBroadCastConsultationRequestFromFireStoreAndSaveToDatabase(specialityName,{},{})
        mModel.getRequestCaseSummaryById(requestId).observe(lifecycleOwner, Observer {
           it?.let{
               mView?.showPatientInfoFromConsultationRequest(it)
               it.caseSummaryVO?.toMutableList()?.let { it1 -> mView?.showSpecialQuestionAnswer(it1) }
           }
        })

    }


    override fun onTapStartConsultation(lifecycleOwner: LifecycleOwner,lifeConsultationRequestVO: ConsultationRequestVO) {
        mModel.getDoctorbyEmail(SessionManager.doctor_email.toString()).observe(lifecycleOwner, Observer {
            mModel.getConsultationRequestByIdAndAddDoctor(lifeConsultationRequestVO.id,
            it,
            onSuccess = {
                mView?.navigateToChat(lifeConsultationRequestVO)

            },
            onFailure = {})
        })


    }


}