package com.padc.doctor.mvp.presenter.impls

import androidx.lifecycle.LifecycleOwner
import com.padc.doctor.mvp.presenter.RegisterPresenter
import com.padc.doctor.mvp.views.RegisterView
import com.padc.shared.data.models.AuthenticationModel
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.AuthenticationModelImpl
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.DoctorVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter

class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {
    private val mAuthenticatioModel: AuthenticationModel = AuthenticationModelImpl

    private val mModel: HealthCareModel = HealthCareModelImpl
    override fun onTapRegister(
        email: String,
        name: String,
        password: String,
        phoneNumber: String,
        speciality: String,
        degree: String,
        biography: String,
        experience: String,
        DOB: String,
        photo: String,
        address: String
    ) {


        var doctorVo = DoctorVO(
            id = email,
            email = email,
            name = name,
            password = password,
            phoneNumber = phoneNumber,
            specialityName = speciality,
            degree = degree,
            biography = biography,
            experience = experience,
            DOB = DOB,
            photo = photo,
            address = address
        )
        mAuthenticatioModel.registerDoctor(
            email,
            name,
            password,
            phoneNumber,
            speciality,
            degree,
            biography,
            experience,
            DOB,
            photo,
            address,
            onSuccess = {
                mModel.registerNewDoctor(doctorVo, onSuccess = {
                    mModel.getDoctorFromFirebaseApiAndSaveToDatabase({},{})
                    mView?.navigateToLoginActivity()
                }, onFailure = {
                })
            },
            onFailure = {})
    }

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {

    }
}




