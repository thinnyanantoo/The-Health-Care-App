package com.padc.shared.data.models

import com.padc.shared.network.auth.AuthManager

interface AuthenticationModel {
    var mAuthManager : AuthManager
    fun loginDoctor(
        email: String,
        name: String,
        password: String,
        phoneNumber: String,
        speciality: String,
        degree: String,
        photo: String,
        address : String,
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
        photo: String,
        address : String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun loginPatient(
        email: String,
        DOB : String,
        gender : String,
        photo : String,
        name: String,
        address: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun registerPatient(
        email: String,
        DOB : String,
        gender : String,
        photo : String,
        name: String,
        address: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

}