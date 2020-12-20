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
import com.padc.doctor.activities.ChatActivity.Companion.newIntent
import com.padc.doctor.adapters.ChatAdapter
import com.padc.doctor.adapters.RequestAdapter
import com.padc.doctor.adapters.SpcialQuestionPatientInfoAdapter
import com.padc.doctor.mvp.presenter.ChatPresenter
import com.padc.doctor.mvp.presenter.impls.ChatPresenterImpl
import com.padc.doctor.mvp.views.ChatView
import com.padc.doctor.utils.SessionManager
import com.padc.shared.activity.BaseActivity
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.*
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.activity_patient_info.*
import kotlinx.android.synthetic.main.item_patient_info.*

class ChatActivity : BaseActivity() , ChatView{
    private lateinit var mPresenter : ChatPresenter
    private lateinit var mAdapter : SpcialQuestionPatientInfoAdapter
    private lateinit var mChatAdapter : ChatAdapter
    private var mModel : HealthCareModel = HealthCareModelImpl
    var Cid : String = ""
    var name : String = ""
    var sid : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setUpPresenter()
        setUpAdapter()
        setUpListener()

        Cid = intent.getStringExtra(CONSULTATIONREQUESTID).toString()
        name = intent.getStringExtra(SPECIALITYNAME).toString()
        sid = intent.getStringExtra(SPECIALITYID).toString()
        Log.d("sid",sid)
        Log.d("sname",name)
        Log.d("CCNNNXXXBBID", Cid)
        SessionManager.con_id = Cid
        mPresenter.onUiReady(Cid,name,sid,this)
      //  mModel.deleteRequestFromDatabase()
    }

    companion object {
        var CONSULTATIONREQUESTID =  "CONSULTATIONREQUESTID"
        var SPECIALITYNAME = " SPECIALITYNAME"
        var SPECIALITYID = " SPECIALITYID"
        fun newIntent(context : Context,id: String,specialityName: String,specialityId: String) : Intent {
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
           mPresenter.onTapMedicineButton(name,sid,Cid)
        }
        btnQuestion.setOnClickListener {
            mPresenter.onTapQuestionButton(name,sid,Cid)
        }

        btnSendMessageInChat.setOnClickListener {
            if (!etMessage.text?.equals("")!!) {
                val text = etMessage.text.toString()
                mPresenter.onTapSendIcon(Cid, text, "")
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
        mAdapter.setNewData(caseSummaryVO)
    }

    var caseSummaryVOList : List<CaseSummaryVO> = listOf()
    override fun showPatientInfo(patientVO: PatientVO) {
//        tvpatientname.text = consultationVO.patientVO?.pname
//        etHeightInChat.text = consultationVO.patientVO?.height
//        etbloodTypeInChat.text = consultationVO.patientVO?.bloodType
//        etWeightInChat.text = consultationVO.patientVO?.weight
//        etwrongInChat.text = consultationVO.patientVO?.allergicMedicine
//        etBloodPressureinChat.text = consultationVO.patientVO?.bloodPressure
//        etNameInChat.text = consultationVO.patientVO?.pname
//        etBdInChat.text = consultationVO.patientVO?.DOB

        tvpatientname.text = patientVO?.pname
        etHeightInChat.text = patientVO?.height
        etbloodTypeInChat.text = patientVO?.bloodType
        etWeightInChat.text = patientVO?.weight
        etwrongInChat.text = patientVO?.allergicMedicine
        etBloodPressureinChat.text = patientVO?.bloodPressure
        etNameInChat.text = patientVO?.pname
        etBdInChat.text = patientVO?.DOB

//        consultationVO.caseSummary?.let {
//            caseSummaryVOList[0].question = consultationVO.caseSummary?.get(0)?.question
//            caseSummaryVOList[0].answer = consultationVO.caseSummary?.get(0)?.answer
//
//            caseSummaryVOList[1].question = consultationVO.caseSummary?.get(1)?.question
//            caseSummaryVOList[1].answer = consultationVO.caseSummary?.get(1)?.answer
//        }

    }

//    override fun showPatientInfo(consultationRequestVO: ConsultationRequestVO) {
//        tvpatientname.text = consultationRequestVO.patientVO?.pname
//        etHeightInChat.text = consultationRequestVO.patientVO?.height
//        etbloodTypeInChat.text = consultationRequestVO.patientVO?.bloodType
//        etWeightInChat.text = consultationRequestVO.patientVO?.weight
//        etwrongInChat.text = consultationRequestVO.patientVO?.allergicMedicine
//        etBloodPressureinChat.text = consultationRequestVO.patientVO?.bloodPressure
//        etNameInChat.text = consultationRequestVO.patientVO?.pname
//        etBdInChat.text = consultationRequestVO.patientVO?.DOB
//
//    }

    override fun navigateToQuestionActivity(specialityName: String,specialityId: String,consultId: String) {
        startActivity(ShowQuestionActivity.newIntent(this,specialityName,specialityId,consultId))
    }

    override fun navigateToMedicineActivity(specialityName: String, specialityId: String,consultId: String) {
         startActivity(ShowMedicineActivity.newIntent(this,specialityName,specialityId,consultId))
    }

    override fun displayPatientChat(chat: List<ChatMessageVO>) {
         mChatAdapter.setNewData(
             chat)
    }


}