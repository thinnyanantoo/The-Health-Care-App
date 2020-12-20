package com.padc.the_health_care_app.mvp.views

import com.padc.shared.data.vos.CaseSummaryVO
import com.padc.shared.data.vos.ChatMessageVO
import com.padc.shared.data.vos.MedicineVO
import com.padc.shared.data.vos.PatientVO
import com.padc.shared.mvp.views.BaseView

interface PatientChatView : BaseView {


    fun navigateToCheckout()

    fun showSpeciality(id : String)
    fun displayPatientChat(list : List<ChatMessageVO>)

    fun navigateToSpecialQuestionByDoctor()

    fun displayPrescription(list :List<MedicineVO>)

    fun navigateToCheckOut(id : String)

    fun showSendMessageLayout()

    fun displayPatientInfo(patient : PatientVO)

    fun displayPatientQuestion(List: ArrayList<CaseSummaryVO>)
}