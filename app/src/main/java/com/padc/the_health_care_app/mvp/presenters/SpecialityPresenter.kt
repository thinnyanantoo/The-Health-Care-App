package com.padc.the_health_care_app.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.data.vos.PatientVO
import com.padc.shared.data.vos.SpecialityVO
import com.padc.shared.mvp.presenters.BasePresenter
import com.padc.the_health_care_app.delegates.SpecialityItemDelegate
import com.padc.the_health_care_app.mvp.views.SpecialityView

interface SpecialityPresenter : BasePresenter<SpecialityView>,SpecialityItemDelegate {
    fun onUiReady(lifeCycleOwner : LifecycleOwner,patientId : String)

    fun onUiReadyForConsultatinConfrim(lifeCycleOwner: LifecycleOwner)
    fun onTapCancelInDialog(lifeCycleOwner: LifecycleOwner)
    fun onTapSureInDialog(specialityVO: SpecialityVO,patientId: String)

    fun onTapStartConsultation(consultationId: String, consultationRequestVO: ConsultationRequestVO)


}