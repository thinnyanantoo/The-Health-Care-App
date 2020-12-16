package com.padc.shared.network.auth

import com.padc.shared.data.vos.PatientVO

interface AuthManager {
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