package com.padc.the_health_care_app.mvp.presenters.impls

import com.padc.shared.data.models.AuthenticationModel
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.AuthenticationModelImpl
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.PatientVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter
import com.padc.the_health_care_app.mvp.presenters.RegisterPresenter
import com.padc.the_health_care_app.mvp.views.RegisterView
import java.util.*

class RegisterPresenterImpl : RegisterPresenter ,AbstractBasePresenter<RegisterView>() {
    private val mAuthenticationModel : AuthenticationModel = AuthenticationModelImpl
    private val mModel : HealthCareModel = HealthCareModelImpl
    override fun onTapRegister(username: String, email: String, password: String) {


            val patientVO = PatientVO(
                id = email,
                pname = username,
                email = email,
                password = password

            )
            mAuthenticationModel.registerPatient(username,email,password,onSuccess = {
                mModel.registerNewPatient(patientVO,onSuccess = {
                    mView?.navigateToLoginScreen()
                },onFailure = {})
            },onFailure = {
            })

    }
}