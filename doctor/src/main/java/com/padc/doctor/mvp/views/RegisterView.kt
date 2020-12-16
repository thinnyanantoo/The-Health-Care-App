package com.padc.doctor.mvp.views

import androidx.lifecycle.LifecycleOwner
import com.padc.shared.data.vos.DoctorVO
import com.padc.shared.mvp.views.BaseView


interface RegisterView : BaseView {
    fun navigateToLoginActivity()
}