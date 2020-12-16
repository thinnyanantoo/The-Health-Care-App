package com.padc.the_health_care_app.mvp.presenters

interface RegisterPresenter {
    fun onTapRegister(username: String, email: String, password : String)
}