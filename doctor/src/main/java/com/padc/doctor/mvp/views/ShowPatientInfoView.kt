package com.padc.doctor.mvp.views

import com.padc.shared.data.vos.CaseSummaryVO
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.data.vos.PatientVO
import com.padc.shared.mvp.views.BaseView

interface ShowPatientInfoView : BaseView {
    fun showPatientInfoFromConsultationRequest(consultationRequestVO: ConsultationRequestVO)
    fun showSpecialQuestionAnswer(caseSummaryVO: List<CaseSummaryVO>)
    fun navigateToChat(consultationRequestVO: ConsultationRequestVO)
}