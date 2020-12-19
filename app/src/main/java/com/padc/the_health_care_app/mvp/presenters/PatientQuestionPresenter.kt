package com.padc.the_health_care_app.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padc.shared.data.vos.*
import com.padc.shared.mvp.presenters.BasePresenter
import com.padc.the_health_care_app.delegates.SpecialQuestionDelegate
import com.padc.the_health_care_app.mvp.views.PatientQuestionView

interface PatientQuestionPresenter :BasePresenter<PatientQuestionView>,SpecialQuestionDelegate{
    fun onUiReadyForSpecialQuestion(specialityId : String, documentId: String,lifecycleOwner: LifecycleOwner)

    fun onUiReadyForGeneralOneTimeQuestion(question : String,lifecycleOwner: LifecycleOwner)
    fun onUiReadyForGeneralAlwaysQuestion(question: String,lifecycleOwner: LifecycleOwner)


    fun onTapContinueOnFirstGeneralQuestion(patientVO: PatientVO)
    fun onTapContinueOnSecondGeneralQuestion(specialityQuestion : List<SpecialQuestionVO>)
    fun onTapConfirmConsultation(list: ArrayList<CaseSummaryVO>)
}