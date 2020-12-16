package com.padc.the_health_care_app.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.padc.shared.activity.BaseActivity
import com.padc.the_health_care_app.R
import com.padc.the_health_care_app.mvp.presenters.RegisterPresenter
import com.padc.the_health_care_app.mvp.presenters.impls.RegisterPresenterImpl
import com.padc.the_health_care_app.mvp.views.RegisterView
import kotlinx.android.synthetic.main.activity_patient_register.*

class PatientRegisterActivity : BaseActivity(), RegisterView {
    private lateinit var mPresenter: RegisterPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_register)
        setUpPresenter()
        setUpActionListener()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, PatientRegisterActivity::class.java)
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(RegisterPresenterImpl::class.java)
        (mPresenter as RegisterPresenterImpl).initPresenter(this)
    }

    override fun navigateToLoginScreen() {
        startActivity(PatientLoginActivity.newIntent(this))
        this.finish()
    }

    private fun setUpActionListener() {
        btnRegister.setOnClickListener {
            mPresenter.onTapRegister(
                etEnterNameInRegister.text.toString(),
                etEnterPhoneNumberInRegister.text.toString(),
                etEnterPasswordInRegister.text.toString()
            )
        }
    }
}