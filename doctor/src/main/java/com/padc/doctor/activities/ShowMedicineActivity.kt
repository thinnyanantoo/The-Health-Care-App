package com.padc.doctor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.doctor.R
import com.padc.doctor.adapters.ShowMedicineAdapter
import com.padc.doctor.adapters.ShowQuestionAdapter
import com.padc.doctor.mvp.presenter.PrescriptionMedicinePresenter
import com.padc.doctor.mvp.presenter.ShowSpecialQuestionPresenter
import com.padc.doctor.mvp.presenter.impls.PrescriptionMedicinePresenterImpl
import com.padc.doctor.mvp.presenter.impls.ShowSpecialQuestionPresenterImpl
import com.padc.doctor.mvp.views.PrescriptionMedicineView
import com.padc.doctor.mvp.views.ShowSpecialQuestionView
import com.padc.shared.activity.BaseActivity
import com.padc.shared.data.vos.MedicineVO
import com.padc.shared.data.vos.PresriptionVO
import com.padc.shared.data.vos.SpecialQuestionVO
import kotlinx.android.synthetic.main.activity_show_medicine.*
import kotlinx.android.synthetic.main.activity_show_question.*

class ShowMedicineActivity : BaseActivity(), PrescriptionMedicineView {
        private lateinit var mPresenter: PrescriptionMedicinePresenter
        private lateinit var mAdapter : ShowMedicineAdapter
        var name= ""
        var id= ""
        var cid = ""
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_show_medicine)
            setUpPresenter()
            setUpAdapter()
            name = intent.getStringExtra(SPECIALITYNAME).toString()
            id = intent.getStringExtra(SPECIALITYID).toString()
            cid = intent.getStringExtra(CONSULTID).toString()
            mPresenter.onUiReady(name,id,cid,this)
            Log.d("SSSID",id)
            Log.d("SSSNAME",name)
            Log.d("CCCCIDD",cid)
        }


        private fun setUpPresenter(){
            mPresenter = ViewModelProviders.of(this).get(PrescriptionMedicinePresenterImpl::class.java)
            mPresenter.initPresenter(this)
        }


        private fun setUpAdapter(){
            mAdapter = ShowMedicineAdapter(mPresenter)
            val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            rvShowMedicine.layoutManager = linearLayoutManager
            rvShowMedicine.adapter = mAdapter

        }

        companion object {
            val SPECIALITYNAME = "SPECIALITYNAME"
            val SPECIALITYID = "SPECIALITYID"
            val CONSULTID = "CONSULTID"
            fun newIntent(context: Context, name : String, id : String, consultId : String): Intent {
                var intent = Intent(context,ShowMedicineActivity::class.java)
                intent.putExtra(SPECIALITYNAME,name)
                intent.putExtra(SPECIALITYID,id)
                intent.putExtra(CONSULTID, consultId)
                return intent
            }
        }

    override fun showPrescriptionMedicine(medicine: List<MedicineVO>) {
        mAdapter.setNewData(medicine)
    }
    }