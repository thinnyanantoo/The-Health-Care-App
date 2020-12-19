package com.padc.doctor.utils

import android.content.Context
import android.content.SharedPreferences
import com.padc.shared.Util.*

object SessionManager {
    private const val NAME = sharePreferenceDoctor
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }


    var doctor_name: String?

        get() = preferences.getString(sharePreferenceName, "")

        set(value) = preferences.edit {
            it.putString(sharePreferenceName, value)
        }

    var doctor_email: String?

        get() = preferences.getString(sharePreferenceEmail, "")
        set(value) = preferences.edit {
            it.putString(sharePreferenceEmail, value)
        }

    var doctor_id: String?

        get() = preferences.getString(sharePreferenceID, "")

        set(value) = preferences.edit {
            it.putString(sharePreferenceID, value)
        }

    var request_id: String?

        get() = preferences.getString(sharePreferenceRequestID, "request000")

        set(value) = preferences.edit {
            it.putString(sharePreferenceRequestID, value)
        }

    var doctor_device_token: String?

        get() = preferences.getString(sharePreferenceDeviceID, "")

        set(value) = preferences.edit {
            it.putString(sharePreferenceDeviceID, value)
        }

    var doctor_dateOfBirth: String?

        get() = preferences.getString(sharePreferenceDateOfBirth, "")

        set(value) = preferences.edit {
            it.putString(sharePreferenceDateOfBirth, value)
        }

    var doctor_speciality: String?

        get() = preferences.getString(sharePreferenceSpeciality, "")

        set(value) = preferences.edit {
            it.putString(sharePreferenceSpeciality, value)
        }






    var doctor_comment : String?

        get() = preferences.getString(sharePreferenceComment, "")

        set(value) = preferences.edit {
            it.putString(sharePreferenceComment, value)
        }



    var doctor_image : String?

        get() = preferences.getString(sharePreferenceImage, "")

        set(value) = preferences.edit {
            it.putString(sharePreferenceImage, value)
        }

    var doctor_photo : String?

        get() = preferences.getString(sharePreferencePhoto, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePhoto, value)
        }




    //////////////////////////


}