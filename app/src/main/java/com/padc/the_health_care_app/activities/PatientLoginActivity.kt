package com.padc.the_health_care_app.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.padc.shared.activity.BaseActivity
import com.padc.shared.data.vos.PatientVO
import com.padc.the_health_care_app.R
import com.padc.the_health_care_app.fragments.HomeFragment
import com.padc.the_health_care_app.mvp.presenters.SignInPresenter
import com.padc.the_health_care_app.mvp.presenters.impls.SignInPresenterImpl
import com.padc.the_health_care_app.mvp.views.RegisterView
import com.padc.the_health_care_app.mvp.views.SignInView
import kotlinx.android.synthetic.main.activity_patient_login.*
import kotlinx.android.synthetic.main.activity_patient_register.*

class PatientLoginActivity : BaseActivity(),SignInView{
    private lateinit var mPresenter : SignInPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_login)
        setUpPresenter()
        setUpActionListener()
        mPresenter.onUiReady(this)

    }

    companion object {
        fun newIntent(context : Context) : Intent {
            return Intent(context,PatientLoginActivity::class.java)
        }
    }



    private fun setUpPresenter(){
        mPresenter= ViewModelProviders.of(this).get(SignInPresenterImpl::class.java)
        (mPresenter as SignInPresenterImpl).initPresenter(this)
    }

    private fun setUpActionListener(){
        btnLogin.setOnClickListener {
            mPresenter.onTapLogin(etEnterPhoneNumber.text.toString(),etEnterPassword.text.toString(),this)

            tvSignUp.setOnClickListener{
                mPresenter.onTapRegister()
            }
        }
    }


    override fun navigateToRegisterScreen() {
        startActivity(PatientRegisterActivity.newIntent(this))
    }

    override fun navigateToHomeScreen(patientVO: PatientVO) {
      startActivity(MainActivity.newIntent(this,patientId = patientVO.id,patientName = patientVO.pname.toString()))
    }
}