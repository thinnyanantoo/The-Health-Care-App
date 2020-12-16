package com.padc.shared.data.models.impls

import com.padc.shared.data.models.AuthenticationModel
import com.padc.shared.data.vos.PatientVO
import com.padc.shared.network.auth.AuthManager
import com.padc.shared.network.auth.FirebaseAuthManager

object AuthenticationModelImpl : AuthenticationModel{
    override var mAuthManager: AuthManager
        get() = FirebaseAuthManager
        set(value) {}

    override fun loginDoctor(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.loginDoctor(email, password, onSuccess, onFailure)
    }

    override fun registerDoctor(
        email: String,
        name: String,
        password: String,
        phoneNumber: String,
        speciality: String,
        degree: String,
        biography : String,
        experience : String,
        DOB : String,
        photo: String,
        address: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.registerDoctor(email, name, password, phoneNumber, speciality, degree, biography, experience, DOB, photo, address, onSuccess, onFailure)
    }

    override fun loginPatient(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.loginPatient(email,password,onSuccess = onSuccess,onFailure = onFailure)
    }

    override fun registerPatient(
        email: String,
        password: String,
        name: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.registerPatient(email,password,name,onSuccess,onFailure)
    }

}