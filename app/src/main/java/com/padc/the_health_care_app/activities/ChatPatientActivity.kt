package com.padc.the_health_care_app.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.padc.shared.activity.BaseActivity
import com.padc.the_health_care_app.R
import com.padc.the_health_care_app.mvp.presenters.PatientChatPresenter
import com.padc.the_health_care_app.mvp.presenters.impls.PatientChatPresenterImpl
import com.padc.the_health_care_app.mvp.views.PatientChatView

class ChatPatientActivity : BaseActivity(), PatientChatView {
    private lateinit var mPresenter : PatientChatPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_patient)
        setUpPresenter()
    }

    companion object {
        var ID = "ID"
        fun newIntent(context: Context,id : String) : Intent {
            var intent = Intent(context,ChatPatientActivity::class.java)
            intent.putExtra(ID , id)
            return intent
        }
    }


    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(PatientChatPresenterImpl::class.java)
        mPresenter.initPresenter(this)

    }

}