package com.padc.doctor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.padc.doctor.R
import com.padc.doctor.mvp.presenter.LoginPresenter
import com.padc.doctor.mvp.presenter.impls.LoginPresenterImpl
import com.padc.doctor.mvp.views.LoginView
import com.padc.shared.activity.BaseActivity
import com.padc.shared.data.vos.DoctorVO
import kotlinx.android.synthetic.main.activity_doctor_log_in.*

class DoctorLoginActivity : BaseActivity(), LoginView {

    private lateinit var mPresenter : LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_log_in)

        setUpPresenter()
        setUpListener()
       mPresenter.onUiReady(lifecycleOwner = this)
    }

    companion object{
        fun newIntent(context: Context) : Intent {
            val intent = Intent(context,DoctorLoginActivity::class.java)
            return intent
        }
    }

    override fun navigateToRegister() {
        startActivity(RegisterActivity.newIntent(this))
    }

    override fun navigateToHomeScreen(doctorVO: DoctorVO) {
        Log.d("NAMEDD",doctorVO.name)
        startActivity(HomeScreenActivity.newIntent(this,doctorVO.id,doctorVO.specialityName,doctorVO.name,doctorVO.photo!!))
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(LoginPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpListener(){
        btnLogin.setOnClickListener {
            mPresenter.onTapLogin(etEnterPhoneNumber.text.toString(),etEnterPassword.text.toString(),this)
        }

        tvSignUp.setOnClickListener {
            mPresenter.onTapRegister()
        }


    }


}