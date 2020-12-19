package com.padc.the_health_care_app.mvp.views

import com.padc.shared.data.vos.CaseSummaryVO
import com.padc.shared.data.vos.GeneralQuestionVO
import com.padc.shared.data.vos.OnetimeGeneralQuestionVO
import com.padc.shared.data.vos.SpecialQuestionVO
import com.padc.shared.mvp.views.BaseView

interface PatientQuestionView : BaseView {
    fun showSecondQuestion()
    fun replaceAnswerList(position : Int, caseSummaryVO: CaseSummaryVO)
    fun showSpecialQuestion(speicalQuestion : List<SpecialQuestionVO>)

    fun navigateToConfirmRequestScreen(id : String)

}