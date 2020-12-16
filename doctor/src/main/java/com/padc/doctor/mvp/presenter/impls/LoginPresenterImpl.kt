package com.padc.doctor.mvp.presenter.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padc.doctor.mvp.presenter.LoginPresenter
import com.padc.doctor.mvp.views.LoginView
import com.padc.shared.data.models.AuthenticationModel
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.AuthenticationModelImpl
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.DoctorVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter

class LoginPresenterImpl : LoginPresenter,AbstractBasePresenter<LoginView>() {
    private val mAuthenticatioModel: AuthenticationModel = AuthenticationModelImpl

    private val mModel : HealthCareModel = HealthCareModelImpl
    override fun onTapLogin(email: String, password: String,lifecycleOwner: LifecycleOwner) {
        if(email.isEmpty() || password.isEmpty()){
                 Log.d("Error", "failed all text field")
        } else  {
            mAuthenticatioModel.loginDoctor(email,password,onSuccess = {
            },onFailure = {})
        //    mModel.getDoctorFromFirebaseApiAndSaveToDatabase({},{})
            mModel.getDoctorbyEmail(email).observe(lifecycleOwner, Observer {
                Log.d("DOCTORVO",it.address.toString())
//                var doctorVO = DoctorVO(
//                    email = email,
//                    password = password
//                )
                Log.d("EmailDoctor",it.email.toString())
                mView?.navigateToHomeScreen(it)
            })
        }
    }

    override fun onTapRegister() {
        mView?.navigateToRegister()
    }

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {

    }


}