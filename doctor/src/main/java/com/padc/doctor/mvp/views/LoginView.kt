package com.padc.doctor.mvp.views

import com.padc.shared.data.vos.DoctorVO
import com.padc.shared.mvp.views.BaseView

interface LoginView : BaseView{
    fun navigateToRegister()
    fun navigateToHomeScreen(doctorVO : DoctorVO)
}