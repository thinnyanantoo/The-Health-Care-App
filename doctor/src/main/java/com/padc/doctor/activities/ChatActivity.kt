package com.padc.doctor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.doctor.R
import com.padc.doctor.adapters.ChatAdapter
import com.padc.doctor.adapters.RequestAdapter
import com.padc.doctor.adapters.SpcialQuestionPatientInfoAdapter
import com.padc.doctor.mvp.presenter.ChatPresenter
import com.padc.doctor.mvp.presenter.impls.ChatPresenterImpl
import com.padc.doctor.mvp.views.ChatView
import com.padc.shared.activity.BaseActivity
import com.padc.shared.data.vos.*
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.activity_patient_info.*
import kotlinx.android.synthetic.main.item_patient_info.*

class ChatActivity : BaseActivity() , ChatView{
    private lateinit var mPresenter : ChatPresenter
    private lateinit var mAdapter : SpcialQuestionPatientInfoAdapter
    private lateinit var mChatAdapter : ChatAdapter
    var id : String = ""
    var name : String = ""
    var sid : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setUpPresenter()
        setUpAdapter()
        setUpListener()

        id = intent.getStringExtra(CONSULTATIONREQUESTID).toString()
        name = intent.getStringExtra(SPECIALITYNAME).toString()
        sid = intent.getStringExtra(SPECIALITYID).toString()
        Log.d("sid",sid)
        Log.d("sname",name)
        mPresenter.onUiReady(id,name,sid,this)
    }

    companion object {
        var CONSULTATIONREQUESTID =  "CONSULTATIONREQUESTID"
        var SPECIALITYNAME = " SPECIALITYNAME"
        var SPECIALITYID = " SPECIALITYID"
        fun newIntent(context : Context,id: String,specialityName : String,specialityId : String) : Intent {
            val intent = Intent(context,ChatActivity::class.java)
            intent.putExtra(CONSULTATIONREQUESTID,id)
            intent.putExtra(SPECIALITYNAME,specialityName)
            intent.putExtra(SPECIALITYID,specialityId)
            return intent
        }
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(ChatPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpListener(){
        btnMedicine.setOnClickListener {
           mPresenter.onTapMedicineButton(name,sid)
        }
        btnQuestion.setOnClickListener {
            mPresenter.onTapQuestionButton(name,sid)
        }

        btnSendMessageInChat.setOnClickListener {
            if (!etMessage.text?.equals("")!!) {
                val text = etMessage.text.toString()
                mPresenter.onTapSendIcon(id, text, "")
                etMessage.text = Editable.Factory.getInstance().newEditable("")
            }
        }

        }

    private fun setUpAdapter(){
        mAdapter = SpcialQuestionPatientInfoAdapter()
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvSpecialQuestionInChat.layoutManager = linearLayoutManager
        rvSpecialQuestionInChat.adapter = mAdapter

        mChatAdapter = ChatAdapter(mPresenter)
        val linearLayoutManagerTwo =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvChatHistory.layoutManager = linearLayoutManagerTwo
        rvChatHistory.adapter = mChatAdapter


    }

    override fun showSpecialQuestionAnswer(caseSummaryVO: List<CaseSummaryVO>) {
        mAdapter.setNewData(caseSummaryVO.toMutableList())
    }

    override fun showPatientInfo(consultationRequestVO: ConsultationRequestVO) {
        tvpatientname.text = consultationRequestVO.patientVO?.pname
        etHeightInChat.text = consultationRequestVO.patientVO?.height
        etbloodTypeInChat.text = consultationRequestVO.patientVO?.bloodType
        etWeightInChat.text = consultationRequestVO.patientVO?.weight
        etwrongInChat.text = consultationRequestVO.patientVO?.allergicMedicine
        etBloodPressureinChat.text = consultationRequestVO.patientVO?.bloodPressure
        etNameInChat.text = consultationRequestVO.patientVO?.pname
        etBdInChat.text = consultationRequestVO.patientVO?.DOB
    }

    override fun navigateToQuestionActivity(specialityName: String,specialityId: String) {
        startActivity(ShowQuestionActivity.newIntent(this,specialityName,specialityId))
    }

    override fun navigateToMedicineActivity(specialityName: String, specialityId: String) {

    }

    override fun displayPatientChat(chat: List<ChatMessageVO>) {
         mChatAdapter.setNewData(chat)
    }


}