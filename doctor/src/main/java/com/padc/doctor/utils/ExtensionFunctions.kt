package com.padc.doctor.utils

import com.padc.shared.data.vos.DoctorVO

fun saveDoctorToSession(doctor : DoctorVO) {
    doctor.id?.let {
        SessionManager.doctor_name = doctor.name
        SessionManager.doctor_id = doctor.id
        SessionManager.doctor_speciality = doctor.specialityName
        SessionManager.doctor_email = doctor.email
        SessionManager.doctor_photo = doctor.photo
        SessionManager.doctor_speciality = doctor.specialityName
        SessionManager.doctor_dateOfBirth = doctor.DOB
        SessionManager.request_id = doctor.deviceId

    }
}