package com.padc.the_health_care_app.mvp.views

import com.padc.shared.data.vos.CaseSummaryVO
import com.padc.shared.data.vos.PatientVO
import com.padc.shared.data.vos.SpecialQuestionVO
import com.padc.shared.mvp.views.BaseView

interface ConfirmPatientDataView : BaseView{
    fun displayCaseSummary(caseSummaryVO: List<CaseSummaryVO>)

    fun displayPatient(patientVO: PatientVO)
    fun navigateToHomeScreen(id : String)
}