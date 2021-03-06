package com.padc.doctor.mvp.views


import com.padc.shared.data.vos.*
import com.padc.shared.mvp.views.BaseView


interface ChatView : BaseView {
    fun showSpecialQuestionAnswer(caseSummaryVO: List<CaseSummaryVO>)
    fun showPatientInfo(patientVO: PatientVO)

    fun navigateToQuestionActivity(specialityName : String,specialityId : String,consultId: String)
    fun navigateToMedicineActivity(specialityName: String,specialityId: String,consultId: String)

    fun displayPatientChat(chat : List<ChatMessageVO>)
    fun displayPrescription(Lists: List<PresriptionVO>)

}