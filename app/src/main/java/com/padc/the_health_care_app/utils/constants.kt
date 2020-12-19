package com.padc.the_health_care_app.utils

import com.padc.shared.data.vos.PatientVO

fun addToPatient() : PatientVO{
    val patient = PatientVO()
    patient.id = SessionManager.patient_id.toString()
    patient.photo = SessionManager.patient_photo.toString()
    patient.pname = SessionManager.patient_name.toString()
    patient.email = SessionManager.patient_email.toString()
    return patient
}

