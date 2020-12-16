package com.padc.the_health_care_app.mvp.views

import com.padc.shared.data.vos.PatientVO
import com.padc.shared.mvp.views.BaseView

interface SignInView : BaseView{
    fun navigateToRegisterScreen()
    fun navigateToHomeScreen(patientVO: PatientVO)
    fun showError(message: String)
}