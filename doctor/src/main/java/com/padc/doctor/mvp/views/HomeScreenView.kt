package com.padc.doctor.mvp.views

import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.data.vos.DoctorVO
import com.padc.shared.mvp.views.BaseView
import com.padc.shared.persistence.daos.ConsultationRequestDao

interface HomeScreenView : BaseView{
    fun showConsultationRequest(consultationRequestVO: List<ConsultationRequestVO>)
    fun navigateToPatientCaseSummaryInfo(consultationrequestId : ConsultationRequestVO)
    fun displayEmptyView()
}