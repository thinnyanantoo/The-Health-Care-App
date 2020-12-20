package com.padc.the_health_care_app.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.shared.activity.BaseActivity
import com.padc.shared.data.vos.CaseSummaryVO
import com.padc.shared.data.vos.ChatMessageVO
import com.padc.shared.data.vos.MedicineVO
import com.padc.shared.data.vos.PatientVO
import com.padc.the_health_care_app.R
import com.padc.the_health_care_app.adapters.ChatHistoryAdapter
import com.padc.the_health_care_app.adapters.SpecialQuestionAdapter
import com.padc.the_health_care_app.adapters.SpecialQuestionReviewAdapter
import com.padc.the_health_care_app.mvp.presenters.PatientChatPresenter
import com.padc.the_health_care_app.mvp.presenters.impls.PatientChatPresenterImpl
import com.padc.the_health_care_app.mvp.views.PatientChatView
import com.padc.the_health_care_app.utils.SessionManager
import kotlinx.android.synthetic.main.activity_chat_patient.*
import kotlinx.android.synthetic.main.item_patient_info_in_chart.*

class ChatPatientActivity : BaseActivity(), PatientChatView {
    private lateinit var mPresenter : PatientChatPresenter
    private lateinit var mSpecalityAdapter : SpecialQuestionReviewAdapter
    private lateinit var mChatAdapter : ChatHistoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_patient)
        setUpPresenter()
        setUpAdapter()
        setUpListener()

        mPresenter.onUiReady(intent.getStringExtra(ID).toString(),this)

    }

    companion object {
        var ID = "ID"
        var patientId = "PATIENTID"
        fun newIntent(context: Context,id : String) : Intent {
            var intent = Intent(context,ChatPatientActivity::class.java)
            intent.putExtra(ID , id)
            Log.d("IdForRequest",id)
            return intent
        }
    }


    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(PatientChatPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpAdapter(){
        mSpecalityAdapter = SpecialQuestionReviewAdapter()
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvSpecialQuestionInChatForRview.layoutManager = linearLayoutManager
        rvSpecialQuestionInChatForRview.adapter = mSpecalityAdapter

        mChatAdapter = ChatHistoryAdapter(mPresenter)
        val linearLayoutManagerTwo =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvChatHistory.layoutManager = linearLayoutManagerTwo
        rvChatHistory.adapter = mChatAdapter
    }

    private fun setUpListener() {
        btnSendMessage.setOnClickListener {
            if (!etMessage.text?.equals("")!!) {
                val text = etMessage.text.toString()
                mPresenter.onTapSend(SessionManager.request_id_for_patient.toString(), text, "")
                etMessage.text = Editable.Factory.getInstance().newEditable("")
            }
        }
    }

    override fun navigateToCheckout() {
        TODO("Not yet implemented")
    }

    override fun showSpeciality(id: String) {

    }

    override fun displayPatientChat(list: List<ChatMessageVO>) {
        mChatAdapter.setNewData(list)
    }

    override fun navigateToSpecialQuestionByDoctor() {
        TODO("Not yet implemented")
    }

    override fun displayPrescription(list: List<MedicineVO>) {
        when(list.size>0) {
            true -> {
             //   showPre
            }
        }
    }

    override fun navigateToCheckOut(id: String) {
        TODO("Not yet implemented")
    }

    override fun showSendMessageLayout() {
       // layoutSend.visibility = View.VISIBLE
    }

    override fun displayPatientQuestion(List: ArrayList<CaseSummaryVO>) {
        mSpecalityAdapter.setNewData(List)
    }

    override fun displayPatientInfo(patient: PatientVO) {
        etNameInChat.text = patient.pname
        etHeightInChat.text = patient.height
        etWeightInChat.text = patient.weight
        etbloodTypeInChat.text = patient.bloodType
        etBloodPressureinChat.text  = patient.bloodPressure
        etBdInChat.text = patient.DOB
        etwrongInChat.text = patient.allergicMedicine
    }
}