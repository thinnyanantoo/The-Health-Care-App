package com.padc.the_health_care_app.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.shared.activity.BaseActivity
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.CaseSummaryVO
import com.padc.shared.data.vos.GeneralQuestionVO
import com.padc.shared.data.vos.PatientVO
import com.padc.shared.data.vos.SpecialQuestionVO
import com.padc.the_health_care_app.HealthCareApp
import com.padc.the_health_care_app.R
import com.padc.the_health_care_app.adapters.SpecialQuestionAdapter
import com.padc.the_health_care_app.fragments.HomeFragment
import com.padc.the_health_care_app.mvp.presenters.PatientQuestionPresenter
import com.padc.the_health_care_app.mvp.presenters.impls.PatientQuestionPresenterImpl
import com.padc.the_health_care_app.mvp.views.PatientQuestionView
import kotlinx.android.synthetic.main.activity_patient_info_filling_form.*
import kotlinx.android.synthetic.main.activity_patient_register.*
import kotlinx.android.synthetic.main.activity_stepper.*
import kotlinx.android.synthetic.main.fragment_special_question.*
import kotlinx.android.synthetic.main.fragment_special_question.rvSpecialQuestion
import kotlinx.android.synthetic.main.spcialquestion_from.*
import java.util.*
import kotlin.collections.ArrayList


class PatientInfoFillingForm : BaseActivity(), PatientQuestionView {

    var patientid : String = ""
    var patientName : String = ""
    var sid : String = ""
    var sName : String = ""
    var documentId : String = ""
    private lateinit var mPresenter: PatientQuestionPresenter
    private lateinit var mAdapter: SpecialQuestionAdapter
    private var mModel: HealthCareModel = HealthCareModelImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_info_filling_form)
        setUpPresenter()
        setUpAdapter()
        setUpListener()
        stepIndicator.currentStep = 1

          sid = intent.getStringExtra(SID).toString()
        sName = intent.getStringExtra(SNAME).toString()
        patientid = intent.getStringExtra(PATIENTID).toString()
        patientName = intent.getStringExtra(PATIENTNAME).toString()

        mPresenter.onUiReadyForSpecialQuestion(sid,documentId,  this)
    }



    companion object {
        val SID = "ID"
        val SNAME = "SNAME"
        val PATIENTID = "PATIENTID"
        val PATIENTNAME = "PATIENTNAME"
        fun newIntent(context: Context, id: String,Sname : String,patientId : String,patientName: String): Intent {
            val intent = Intent(context, PatientInfoFillingForm::class.java)
            intent.putExtra(SID, id)
            intent.putExtra(SNAME, Sname)
            intent.putExtra(PATIENTID,patientId)
            intent.putExtra(PATIENTNAME,patientName)
            return intent

        }
    }

    private var year: String? = ""
    private var day: String? = ""
    private var month: String? = ""
    private var bloodType: String? = ""


    private var caseSummaryVoList : ArrayList<CaseSummaryVO> = arrayListOf()

    private fun setUpListener() {

        btnContinueInFirst.setOnClickListener {
            pNameInSecond.text = patientName
            etWeightInTwo.text = etAnswerWeight.text
            etBloodWieghtInTwo.text = etAnswerBloodPressure.text
            etwrongInSecond.text = etAnswerWrongMedicine.text
            tvbdAnswerInSecond.text = "$year/$month/$day"
            etheigtAnswerInSecond.text = etAnswerHeight.text
            etbloodTypeAnswerInSecond.text = bloodType
            lineaLayoutOne.visibility = View.GONE
            linearLayoutTwo.visibility = View.VISIBLE
            stepIndicator.currentStep = 1
        }

        btnContinueInTwo.setOnClickListener {
            mModel.addOneTimeGeneralQuestionToPatient(patientid,pNameQauestionINSecond.text.toString(),patientName)
            mModel.addOneTimeGeneralQuestionToPatient(patientid,tvbirthdayQuestionInSecond.text.toString(),"$year/$month/$day".toString())
            mModel.addOneTimeGeneralQuestionToPatient(patientid,tvheightQuestionInSecond.text.toString(),etheigtAnswerInSecond.text.toString())
            mModel.addOneTimeGeneralQuestionToPatient(patientid,tvbloodTypeQuestionInSecond.text.toString(),etbloodTypeAnswerInSecond.text.toString())
            mModel.addOneTimeGeneralQuestionToPatient(patientid,tvWrngQuestionInSecond.text.toString(),etwrongInSecond.text.toString())
            mModel.addOneTimeGeneralQuestionToPatient(patientid,questionWeight.text.toString(),etAnswerWeight.text.toString())
            mModel.addOneTimeGeneralQuestionToPatient(patientid,QuestionBloodPressure.text.toString(),QuestionBloodPressure.text.toString())


            val patientVO  = PatientVO(
                id = patientid,
                pname = pNameInSecond.text.toString(),
                weight = etWeightInTwo.text.toString(),
                height = etAnswerHeight.text.toString(),
                DOB = "$year/$month/$day".toString(),
                allergicMedicine = etwrongInSecond.text.toString(),
                bloodType = etbloodTypeAnswerInSecond.text.toString(),
                bloodPressure = etAnswerBloodPressure.text.toString()
            )
           mModel.insertDatatoPatientVO(patientVO = patientVO)

            linearLayoutTwo.visibility = View.GONE
            linearLayoutThree.visibility = View.VISIBLE
            stepIndicator.currentStep = 2
        }

        btnConfirmInSpecialQuestion.setOnClickListener {
            Log.d("PATIENTIDINFILLINGFORM",patientid)
            mModel.deleteCaseSummary()
         //   val id = UUID.randomUUID().toString()
            mModel.insertCaseSummary(caseSummaryVoList)
            startActivity(PatientCaseSummaryConfirmActivity.newIntent(this,sid,sName, patientid, documentId))

        }

        spinnerYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                year = parent.getItemAtPosition(position).toString()
            }

        }

        spinnerMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                month = parent.getItemAtPosition(position).toString()
            }

        }

        spinnerDay.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                day = parent.getItemAtPosition(position).toString()
            }

        }
        spinnerBloodType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                bloodType = parent.getItemAtPosition(position).toString()
            }

        }

    }

    override fun showSecondQuestion() {
    }

    override fun showSpecialQuestion(speicalQuestion: List<SpecialQuestionVO>) {
        Log.e("specialQuestionList",speicalQuestion.size.toString())
        mAdapter.setNewData(speicalQuestion.toMutableList())

        for(item in speicalQuestion){
            caseSummaryVoList.add(CaseSummaryVO(item.id,item.question,""))
        }

        mAdapter.setCaseSummaryList(caseSummaryList = caseSummaryVoList)

        Log.e("CaseSummaryList",caseSummaryVoList.size.toString())
    }

    override fun navigateToConfirmRequestScreen(id: String) {
        Log.d("idsdf",id)
        documentId = id
      //  mPresenter.onTapConfirmConsultation(id)
        startActivity(PatientCaseSummaryConfirmActivity.newIntent(this, sid, sName,patientid,documentId))
    }

    override fun replaceAnswerList(position: Int, caseSummaryVO: CaseSummaryVO) {
        caseSummaryVoList[position] = caseSummaryVO
        Log.d("MMM",caseSummaryVO.answer.toString())
    }

    private fun setUpAdapter() {
        mAdapter = SpecialQuestionAdapter(mPresenter)
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvSpecialQuestion.layoutManager = linearLayoutManager
        rvSpecialQuestion.adapter = mAdapter
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(PatientQuestionPresenterImpl::class.java)
        (mPresenter as PatientQuestionPresenterImpl).initPresenter(this)
    }
}