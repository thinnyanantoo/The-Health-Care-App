package com.padc.doctor.mvp.views


import com.padc.shared.data.vos.*
import com.padc.shared.mvp.views.BaseView


interface ChatView : BaseView {
    fun showSpecialQuestionAnswer(caseSummaryVO: List<CaseSummaryVO>)
    fun showPatientInfo(consultationRequestVO: ConsultationRequestVO)

    fun navigateToQuestionActivity(specialityName : String,specialityId : String)
    fun navigateToMedicineActivity(specialityName: String,specialityId: String)

    fun displayPatientChat(chat : List<ChatMessageVO>)

}