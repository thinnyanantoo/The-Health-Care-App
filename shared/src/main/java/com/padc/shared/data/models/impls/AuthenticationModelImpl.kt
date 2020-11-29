package com.padc.shared.data.models.impls

import com.padc.shared.data.models.AuthenticationModel
import com.padc.shared.network.auth.AuthManager
import com.padc.shared.network.auth.FirebaseAuthManager

object AuthenticationModelImpl : AuthenticationModel {
    override var mAuthManager : AuthManager = FirebaseAuthManager
    override fun loginDoctor(
        email: String,
        name: String,
        password: String,
        phoneNumber: String,
        speciality: String,
        degree: String,
        photo: String,
        address: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.loginDoctor(email, name, password, phoneNumber, speciality, degree, photo, address, onSuccess, onFailure)
    }

    override fun registerDoctor(
        email: String,
        name: String,
        password: String,
        phoneNumber: String,
        speciality: String,
        degree: String,
        photo: String,
        address: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.registerDoctor(email, name, password, phoneNumber, speciality, degree, photo, address, onSuccess, onFailure)
    }

    override fun loginPatient(
        email: String,
        DOB: String,
        gender: String,
        photo: String,
        name: String,
        address: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.loginPatient(email, DOB, gender, photo, name, address, password, onSuccess, onFailure)
    }

    override fun registerPatient(
        email: String,
        DOB: String,
        gender: String,
        photo: String,
        name: String,
        address: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
       mAuthManager.registerPatient(email, DOB, gender, photo, name, address, password, onSuccess, onFailure)
    }

}