package com.padc.the_health_care_app.utils

import android.content.Context
import android.content.Context.*
import android.content.SharedPreferences
import com.padc.shared.Util.*

object SessionManager {

    private const val NAME = sharePreferencePatient
    private const val MODE = MODE_PRIVATE
    private lateinit var preferences: SharedPreferences


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

//    var login_status: Boolean
//
//        get() = preferences.getBoolean(sharePreferenceLoginStatus, false)
//
//        set(value) = preferences.edit {
//            it.putBoolean(sharePreferenceLoginStatus, value)
//        }

    var patient_name: String?

        get() = preferences.getString(sharePreferencePatientName, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientName, value)
        }

    var patient_email: String?

        get() = preferences.getString(sharePreferencePatientEmail, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientEmail, value)
        }

    var patient_id: String?

        get() = preferences.getString(sharePreferencePatientID, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientID, value)
        }

    var request_id_for_patient: String?

        get() = preferences.getString(sharePreferenceRequestIDforPatient, "request01000")

        set(value) = preferences.edit {
            it.putString(sharePreferenceRequestIDforPatient, value)
        }

    var patient_device_token: String?

        get() = preferences.getString(sharePreferencePatientDeviceID, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientDeviceID, value)
        }

    var patient_dateOfBirth: String?

        get() = preferences.getString(sharePreferencePatientDateOfBirth, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientDateOfBirth, value)
        }
    var patient_dateAllegic: String?

        get() = preferences.getString(sharePreferencePatientAllgicMedicine, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientAllgicMedicine, value)
        }

    var patient_height: String?

        get() = preferences.getString(sharePreferencePatientHeight, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientHeight, value)
        }

    var patient_bloodType: String?

        get() = preferences.getString(sharePreferencePatientBloodType, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientBloodType, value)
        }

    var patient_comment : String?

        get() = preferences.getString(sharePreferencePatientComment, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientComment, value)
        }

    var patient_weight : String?

        get() = preferences.getString(sharePreferencePatientBodyWeight, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientBodyWeight, value)
        }

    var patient_bloodPressure : String?

        get() = preferences.getString(sharePreferencePatientBloodPressure, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientBloodPressure, value)
        }

    var patient_photo : String?

        get() = preferences.getString(sharePreferencePatientPhoto, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientPhoto, value)
        }

}