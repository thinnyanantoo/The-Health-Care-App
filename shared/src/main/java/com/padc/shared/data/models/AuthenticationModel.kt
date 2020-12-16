package com.padc.shared.data.models

import com.padc.shared.data.vos.PatientVO
import com.padc.shared.network.auth.AuthManager

interface AuthenticationModel {
    var mAuthManager : AuthManager
    fun loginDoctor(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun registerDoctor(
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
    )

    fun loginPatient(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun registerPatient(
        name: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

}