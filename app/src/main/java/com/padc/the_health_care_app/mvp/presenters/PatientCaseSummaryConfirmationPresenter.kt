package com.padc.the_health_care_app.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padc.shared.data.vos.CaseSummaryVO
import com.padc.shared.data.vos.DoctorVO
import com.padc.shared.data.vos.PatientVO
import com.padc.shared.data.vos.SpecialityVO
import com.padc.shared.mvp.presenters.BasePresenter
import com.padc.the_health_care_app.mvp.views.ConfirmPatientDataView

interface PatientCaseSummaryConfirmationPresenter : BasePresenter<ConfirmPatientDataView> {
    fun onUiReady(lifecycleOwner: LifecycleOwner)

    fun onReadyForPatient(id: String,lifecycleOwner: LifecycleOwner)

    fun onTapStartConsultationRequest(
        patientVO: PatientVO,
        caseSummaryVO: List<CaseSummaryVO>,
        speciality: String
    )
}