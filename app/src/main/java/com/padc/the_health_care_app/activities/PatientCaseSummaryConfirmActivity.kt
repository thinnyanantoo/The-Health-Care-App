package com.padc.the_health_care_app.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.shared.activity.BaseActivity
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.CaseSummaryVO
import com.padc.shared.data.vos.PatientVO
import com.padc.the_health_care_app.R
import com.padc.the_health_care_app.adapters.CaseSummaryAdapter
import com.padc.the_health_care_app.mvp.presenters.PatientCaseSummaryConfirmationPresenter
import com.padc.the_health_care_app.mvp.presenters.impls.PatientCaseSummaryPresenterImpl
import com.padc.the_health_care_app.mvp.presenters.impls.PatientQuestionPresenterImpl
import com.padc.the_health_care_app.mvp.views.ConfirmPatientDataView
import com.padc.the_health_care_app.utils.SessionManager
import kotlinx.android.synthetic.main.activity_patient_case_summary_confirm.*
import kotlinx.android.synthetic.main.fragment_special_question.*

class PatientCaseSummaryConfirmActivity : BaseActivity(), ConfirmPatientDataView {

    var pVO = PatientVO()
    var sId: String = ""
    var pId: String = ""
    var sName: String = ""
    var documentId: String = ""

    companion object {
        private val SID = "ID"
        private val SNAME = "SNAME"
        private val PATIENTID = "PATIENTID"
        private val DOCUMENTID = "DOCUMENTID"
        fun newIntent(
            context: Context,
            sId: String,
            sName: String,
            pId: String,
            dcoumentID : String
        ): Intent {
            val intent = Intent(context, PatientCaseSummaryConfirmActivity::class.java)
            intent.putExtra(SID, sId)
            intent.putExtra(SNAME, sName)
            intent.putExtra(PATIENTID, pId)
            intent.putExtra(DOCUMENTID,dcoumentID)
            return intent
        }
    }

    private var mModel: HealthCareModel = HealthCareModelImpl
    private lateinit var mPresenter: PatientCaseSummaryConfirmationPresenter
    private lateinit var mAdapter: CaseSummaryAdapter
    private var caseSummaryList: ArrayList<CaseSummaryVO> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_case_summary_confirm)
        setUpPresenter()
        setUpRecycler()

        sId = intent.getStringExtra(SID).toString()
        sName = intent.getStringExtra(SNAME).toString()
        pId = intent.getStringExtra(PATIENTID).toString()
        documentId = intent.getStringExtra(DOCUMENTID).toString()

        Log.d("SpecialityId", sId)
        Log.d("patientID", pId)
        Log.d("SNAME", sName)



        mPresenter.onUiReady(sId, this)
        mPresenter.onReadyForPatient(pId, this)
        setUpListener()

    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(PatientCaseSummaryPresenterImpl::class.java)
        (mPresenter as PatientCaseSummaryPresenterImpl).initPresenter(this)

    }

    private fun setUpListener() {
        btnConfirmRequest.setOnClickListener {
            mPresenter.onTapStartConsultationRequest(
                 documentId,pVO, caseSummaryList, sName, sId
            )

            Log.d("Docskdjsd",documentId)

        }
    }

    private fun setUpRecycler() {
        mAdapter = CaseSummaryAdapter()
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvSpeicalQuestionAndAnswerInConfirm.layoutManager = linearLayoutManager
        rvSpeicalQuestionAndAnswerInConfirm.adapter = mAdapter
    }

    override fun displayCaseSummary(caseSummaryVO: List<CaseSummaryVO>) {
        caseSummaryList.addAll(caseSummaryVO)
        Log.d("LISTSIZE", caseSummaryList.size.toString())
        mAdapter.setNewData(caseSummaryVO.toMutableList())
    }

    override fun navigateToHomeScreen(id : String) {
        SessionManager.request_id_for_patient = id
        startActivity(
            MainActivity.newIntentTwo(this)
        )
    }

    override fun navigateToChatScreen(requestId: String) {
        startActivity(ChatPatientActivity.newIntent(this,requestId))
    }

    override fun displayPatient(patientVO: PatientVO) {
        pNameInConfirm.text = patientVO.pname.toString()
        etWeightInConfirm.text = patientVO.weight.toString()
        etheigtAnswerInConfirm.text = patientVO.height.toString()
        etbloodTypeAnswerInConfirm.text = patientVO.bloodType.toString()
        etBloodPressureInConfirm.text = patientVO.bloodPressure
        tvbdAnswerInConfirm.text = patientVO.DOB
        etwrongInConfirm.text = patientVO.allergicMedicine
        Log.d("name", patientVO.pname.toString())
        Log.d("med", patientVO.allergicMedicine.toString())

        pVO = PatientVO(
            pname = pNameInConfirm.text.toString(),
            bloodPressure = etBloodPressureInConfirm.text.toString(),
            bloodType = etbloodTypeAnswerInConfirm.text.toString(),
            weight = etWeightInConfirm.text.toString(),
            height = etheigtAnswerInConfirm.text.toString(),
            DOB = tvbdAnswerInConfirm.text.toString(),
            allergicMedicine = etwrongInConfirm.text.toString()
        )
    }
}
