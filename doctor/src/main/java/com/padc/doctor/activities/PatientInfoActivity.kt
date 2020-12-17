package com.padc.doctor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.doctor.R
import com.padc.doctor.adapters.RequestAdapter
import com.padc.doctor.adapters.SpcialQuestionPatientInfoAdapter
import com.padc.doctor.mvp.presenter.ShowPatientInfoPresenter
import com.padc.doctor.mvp.presenter.impls.ShowPatientInfoPresenterImpl
import com.padc.doctor.mvp.views.ShowPatientInfoView
import com.padc.shared.activity.BaseActivity
import com.padc.shared.data.vos.CaseSummaryVO
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.data.vos.DoctorVO
import com.padc.shared.data.vos.PatientVO
import kotlinx.android.synthetic.main.activity_patient_info.*
import kotlinx.android.synthetic.main.activity_patient_info.view.*

class PatientInfoActivity : BaseActivity(), ShowPatientInfoView {
    private lateinit var mPresenter : ShowPatientInfoPresenter
    private lateinit var mAdapter : SpcialQuestionPatientInfoAdapter
    var requestID = ""
    var patient : PatientVO  = PatientVO()
    var doctor : DoctorVO = DoctorVO()
    var specialityName = ""
    var specialityId = ""
    var caseSummary : List<CaseSummaryVO>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_info)
        setUpPresenter()
        setUpAdapter()
        setUpListener()
        requestID = intent.getStringExtra(REQUESTID).toString()
        specialityName = intent.getStringExtra(SPECIALITYNAME).toString()
        specialityId = intent.getStringExtra(SPECIALITID).toString()
        mPresenter.onUiReady(requestID,specialityName,specialityId,this)
    }

    companion object {
        val REQUESTID = "REQUESTID"
        val SPECIALITYNAME = "SPECIALITYNAME"
        val SPECIALITID = "SPECIALITYID"
        fun newIntent(context: Context, requestId: String , specialityName : String,specialitId : String): Intent {
            val intent = Intent(context, PatientInfoActivity::class.java)
            intent.putExtra(REQUESTID, requestId)
            intent.putExtra(SPECIALITYNAME,specialityName)
            intent.putExtra(SPECIALITID,specialitId)
            return intent
        }
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(ShowPatientInfoPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpListener(){
        btnStartConsultation.setOnClickListener {
            doctor = DoctorVO(
                id = doctor.id
            )
            mPresenter.onTapStartConsultation(consultationRequestVO = ConsultationRequestVO(
                      id = requestID,
                patientVO = patient,
                caseSummaryVO = caseSummary as ArrayList<CaseSummaryVO>?,
                specialityName = specialityName,
                specialityId = specialityId
            )
            )
        }
    }

    private fun setUpAdapter(){
        mAdapter = SpcialQuestionPatientInfoAdapter()
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvSpeicalQuestionAndAnswerInConfirm.adapter = mAdapter
        rvSpeicalQuestionAndAnswerInConfirm.layoutManager = linearLayoutManager
    }



    override fun showPatientInfoFromConsultationRequest(consultationRequestVO: ConsultationRequestVO) {
        etheigtAnswerInSecond.text = consultationRequestVO.patientVO?.height
        etbloodTypeAnswerInSecond.text = consultationRequestVO.patientVO?.bloodType
        etWeight.text = consultationRequestVO.patientVO?.weight
        etwrongInSecond.text = consultationRequestVO.patientVO?.allergicMedicine
        etBloodPressure.text = consultationRequestVO.patientVO?.bloodPressure
        pNameInSecond.text = consultationRequestVO.patientVO?.pname
        tvbdAnswerInSecond.text = consultationRequestVO.patientVO?.DOB

         patient = PatientVO(
            height = consultationRequestVO.patientVO?.height,
            bloodType = consultationRequestVO.patientVO?.bloodType,
            weight = consultationRequestVO.patientVO?.weight,
             allergicMedicine = consultationRequestVO.patientVO?.allergicMedicine,
            bloodPressure = consultationRequestVO.patientVO?.bloodPressure,
            pname = consultationRequestVO.patientVO?.pname,
            DOB = consultationRequestVO.patientVO?.DOB
        )
    }

    override fun showSpecialQuestionAnswer(caseSummaryVO: List<CaseSummaryVO>) {
        mAdapter.setNewData(caseSummaryVO.toMutableList())
        caseSummary = caseSummaryVO.toMutableList()
    }

    override fun navigateToChat(consultationRequestVO: ConsultationRequestVO) {
        startActivity(ChatActivity.newIntent(this,consultationRequestVO.id,consultationRequestVO.specialityName.toString(),consultationRequestVO.specialityId.toString()))
        Log.d("PSID",consultationRequestVO.specialityId.toString())
        Log.d("PSNAME",consultationRequestVO.specialityName.toString())
    }
}