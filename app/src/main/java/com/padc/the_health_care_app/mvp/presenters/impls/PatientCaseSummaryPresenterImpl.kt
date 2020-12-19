package com.padc.the_health_care_app.mvp.presenters.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.CaseSummaryVO
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.data.vos.PatientVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter
import com.padc.shared.network.CloudFirebaseStoreFirebaseApiImpl
import com.padc.shared.network.FirebaseApi
import com.padc.the_health_care_app.mvp.presenters.PatientCaseSummaryConfirmationPresenter
import com.padc.the_health_care_app.mvp.views.ConfirmPatientDataView
import com.padc.the_health_care_app.utils.SessionManager
import java.util.*
import kotlin.collections.ArrayList

class PatientCaseSummaryPresenterImpl : PatientCaseSummaryConfirmationPresenter,
    AbstractBasePresenter<ConfirmPatientDataView>() {
    private val mModel: HealthCareModel = HealthCareModelImpl
    private val mFirebase: FirebaseApi = CloudFirebaseStoreFirebaseApiImpl
    override fun onUiReady(id : String,lifecycleOwner: LifecycleOwner) {
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


    var id = ""
    override fun onTapStartConsultationRequest(
        documentId : String,
        patientVO: PatientVO,
        caseSummaryVO: List<CaseSummaryVO>,
        speciality: String,
    specialityId : String
    ) {
        mModel.addBroadCastConsultationRequest(documentId,patientVO, caseSummaryVO, speciality,specialityId,
            onSuccess = {
                id = it
                mView?.navigateToHomeScreen(id)
        }, onFailure = {
              Log.e("Error in id","Error in id")
        })
       // mView?.showDialog(consultationRequestVO = ConsultationRequestVO(id = id))

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
