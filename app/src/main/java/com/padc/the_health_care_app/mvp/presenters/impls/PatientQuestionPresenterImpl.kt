package com.padc.the_health_care_app.mvp.presenters.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.*
import com.padc.shared.mvp.presenters.AbstractBasePresenter
import com.padc.the_health_care_app.mvp.presenters.PatientQuestionPresenter
import com.padc.the_health_care_app.mvp.views.PatientQuestionView

class PatientQuestionPresenterImpl : PatientQuestionPresenter,
    AbstractBasePresenter<PatientQuestionView>() {
    private val mModel: HealthCareModel = HealthCareModelImpl
    override fun onUiReadyForSpecialQuestion(
        specialityId : String,
        lifecycleOwner: LifecycleOwner
    ) {
        mModel.getSpecialQuestionBySpecialityNameFromFirebaseAndSaveToDatabase(specialityId,{},{})

        mModel.getSpecialQuestionBySpecialityNameFromDatabase().observe(lifecycleOwner, Observer {
            mView?.showSpecialQuestion(it)
        })
    }

    override fun onUiReadyForGeneralOneTimeQuestion(question: String,lifecycleOwner: LifecycleOwner) {
         mModel.getAllGeneralQuestion(onError = {

         }
        ).observe(lifecycleOwner, Observer {name->
             var arrayList: ArrayList<GeneralQuestionVO> = arrayListOf()
             arrayList.add(name)
             Log.e("arraySize",arrayList.size.toString())
//             mView?.showGeneralOneTimeQuestion(name)
         })
    }

    override fun onUiReadyForGeneralAlwaysQuestion(question: String,lifecycleOwner: LifecycleOwner) {
         mModel.getAllGeneralQuestion (onError = {

         }
        ).observe(lifecycleOwner, Observer {

         })
    }
//

    override fun onTapContinueOnFirstGeneralQuestion(patientVO: PatientVO) {
//        patientVO.onetimeGeneralQuestionVO!!.id?.let {
//            mModel.addOneTimeGeneralQuestionToPatient(
//                patientVO,
//                patientVO.onetimeGeneralQuestionVO!!.id!!,
//                patientVO.onetimeGeneralQuestionVO!!.question,
//                patientVO.onetimeGeneralQuestionVO!!.answer
//            )
//            mView?.showSecondQuestion()
//        }
    }

    override fun onTapContinueOnSecondGeneralQuestion(specialityQuestion : List<SpecialQuestionVO>) {
        mView?.showSpecialQuestion(specialityQuestion)
    }

    override fun onTapConfirmConsultation() {
        mView?.navigateToConfirmRequestScreen()
    }

    override fun onAnswerChange(position: Int, caseSummaryVO: CaseSummaryVO) {
        mView?.replaceAnswerList(position,caseSummaryVO)
    }


}