package com.padc.the_health_care_app.mvp.presenters.impls

import android.util.Log
import android.view.Window
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.padc.shared.data.models.AuthenticationModel
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.AuthenticationModelImpl
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.PatientVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter
import com.padc.the_health_care_app.mvp.presenters.SignInPresenter
import com.padc.the_health_care_app.mvp.views.SignInView

class SignInPresenterImpl : SignInPresenter, AbstractBasePresenter<SignInView>() {
    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
    private val mModel: HealthCareModel = HealthCareModelImpl
    override fun onUiReady(lifecycleOwner: LifecycleOwner) {

    }


    override fun onTapLogin(email: String, password: String, lifecycleOwner: LifecycleOwner) {
        if (email.isEmpty() || password.isEmpty()) {
            mView?.showError("Please enter all fields")
        } else {
            mAuthenticationModel.loginPatient(email, password, onSuccess = {
            }, onFailure = {})
            mModel.getPatientFromFirebaseApiAndSaveToDatabase({},{})
            mModel.getPatientById(email).observe(lifecycleOwner, Observer {
                it?.let {
                    mView?.navigateToHomeScreen(it)
                }

            })

        }
    }

    override fun onTapRegister() {
        mView?.navigateToRegisterScreen()
    }

}