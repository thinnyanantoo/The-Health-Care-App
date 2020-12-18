package com.padc.the_health_care_app.mvp.views

import androidx.lifecycle.LifecycleOwner
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.data.vos.DoctorVO
import com.padc.shared.data.vos.PatientVO
import com.padc.shared.data.vos.SpecialityVO
import com.padc.shared.mvp.views.BaseView

interface SpecialityView : BaseView {
    fun displaySpecialityList(speciality : List<SpecialityVO>)
    fun displayRecentlyDoctor(doctor: DoctorVO)
    fun showError(message : String)
    fun displayFormToFillPatientInfo(speciality: SpecialityVO,patientId : String)
    fun showDialog(speciality: SpecialityVO)

    fun showConsultationRequestReceived(consultation :ConsultationRequestVO)

    fun navigateToChartActivity(consultationId: String, consultationRequestVO: ConsultationRequestVO)
    
}