package com.padc.the_health_care_app.mvp.presenters.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.CaseSummaryVO
import com.padc.shared.data.vos.PatientVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter
import com.padc.shared.network.CloudFirebaseStoreFirebaseApiImpl
import com.padc.shared.network.FirebaseApi
import com.padc.the_health_care_app.mvp.presenters.PatientCaseSummaryConfirmationPresenter
import com.padc.the_health_care_app.mvp.views.ConfirmPatientDataView

class PatientCaseSummaryPresenterImpl : PatientCaseSummaryConfirmationPresenter,
    AbstractBasePresenter<ConfirmPatientDataView>() {
    private val mModel: HealthCareModel = HealthCareModelImpl
    private val mFirebase: FirebaseApi = CloudFirebaseStoreFirebaseApiImpl
    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        mModel.getCaseSummaryFromDb(onError = {
        }).observe(lifecycleOwner, Observer {
            mView?.displayCaseSummary(it)
        })

    }

    override fun onReadyForPatient(id: String, lifecycleOwner: LifecycleOwner) {
         mModel.getPatientFromDb(onError = {}).observe(lifecycleOwner, Observer {
                mView?.displayPatient(it)
                Log.d("DDD", it.DOB.toString())
                Log.d("NNN", it.pname.toString())
            })
    }

    override fun onTapStartConsultationRequest(
        patientVO: PatientVO,
        caseSummaryVO: List<CaseSummaryVO>,
        speciality: String
    ) {
        mModel.addBroadCastConsultationRequest(patientVO, caseSummaryVO, speciality, onSuccess = {
            mView?.navigateToHomeScreen(patientVO)
        }, onFailure = {

        })
    }
}


//    override fun onUiReady(patientVO: PatientVO, lifecycleOwner: LifecycleOwner) {
//        requestPatientAnsweringData(patientVO,lifecycleOwner)
//
//    }
//
//    override fun onTapConfirmConsultation(
//        caseSummaryVO: CaseSummaryVO,
//        patientVO: PatientVO,
//        id: String,
//        specialityVO: SpecialityVO,
//        doctorVO: DoctorVO
//    ) {
//        mFirebase.startConsultation(caseSummaryVO,id,patientVO,doctorVO,onSuccess = {},onFailure = {})
//    }
//
//    private fun requestPatientAnsweringData(patientVO: PatientVO,lifecycleOwner: LifecycleOwner){
//        mModel.getPatientById(patientVO.id).observe(lifecycleOwner, Observer {
//            mView?.displayPatientAnswer(patientVO)
//        })
