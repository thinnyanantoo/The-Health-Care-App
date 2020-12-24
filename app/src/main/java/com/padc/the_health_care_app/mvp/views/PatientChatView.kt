package com.padc.the_health_care_app.mvp.views

import com.padc.shared.data.vos.*
import com.padc.shared.mvp.views.BaseView

interface PatientChatView : BaseView {


//    fun navigateToCheckout()

    fun showSpeciality(id : String)
    fun displayPatientChat(list : List<ChatMessageVO>)
    fun showPrescription(list: List<PresriptionVO>)

    fun displayPatientInfo(patient : PatientVO)

    fun navigateToOrderPrescription()

    fun displayPatientQuestion(List: ArrayList<CaseSummaryVO>)
}