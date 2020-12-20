package com.padc.doctor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.doctor.R
import com.padc.doctor.adapters.RequestAdapter
import com.padc.doctor.mvp.presenter.HomeScreenPresenter
import com.padc.doctor.mvp.presenter.impls.HomeScreenPresenterImpl
import com.padc.doctor.mvp.views.HomeScreenView
import com.padc.doctor.utils.SessionManager
import com.padc.doctor.viewpods.EmptyViewPod
import com.padc.shared.activity.BaseActivity
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.data.vos.DoctorVO
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreenActivity : BaseActivity(), HomeScreenView {

    private lateinit var mPresenter: HomeScreenPresenter
    private lateinit var mRequestAdapter: RequestAdapter

    private lateinit var mViewPodEmpty : EmptyViewPod

    var doctorId: String = ""
    var specialityName: String = ""
    var dName: String = ""
    var dPhoto: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        setUpPresenter()
        setUpAdapter()

        hideEmptyViw()
        setUpViewPods()


        doctorId = intent.getStringExtra(DID).toString()
        specialityName = intent.getStringExtra(DSPECIALITY).toString()
        dName = intent.getStringExtra(DNAME).toString()
        dPhoto = intent.getStringExtra(DPHOTO).toString()
        tvdName.text = dName
        Log.d("specialityName", specialityName.toString())
        mPresenter.onUiReady(specialityName,this)

    }

    companion object {
        val DID = "DID"
        val DSPECIALITY = "DSPECIALITY"
        val DNAME = "DNAME"
        val DPHOTO = "DPHOTO"
        fun newIntent(
            context: Context,
            dId: String,
            dSpecialname: String,
            dName: String,
            dPhoto: String
        ): Intent {
            val intent = Intent(context, HomeScreenActivity::class.java)
            intent.putExtra(DID, dId)
            intent.putExtra(DSPECIALITY, dSpecialname)
            intent.putExtra(DNAME, dName)
            intent.putExtra(DPHOTO, dPhoto)
            return intent
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(HomeScreenPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpAdapter() {
        mRequestAdapter = RequestAdapter(mPresenter)
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvRequest.layoutManager = linearLayoutManager
        rvRequest.adapter = mRequestAdapter
    }


    override fun showConsultationRequest(consultationRequestVO: List<ConsultationRequestVO>) {
        mRequestAdapter.setNewData(consultationRequestVO.toMutableList())

    }

    private fun setUpViewPods(){
        mViewPodEmpty = vpEmpty as EmptyViewPod
        mViewPodEmpty.setEmptyData("", "EMPTY_IMAGE_URL")
        mViewPodEmpty.setDeledate(mPresenter)
    }

    private fun showEmptyView()
    {
        vpEmpty.visibility = View.VISIBLE
    }

    override fun displayEmptyView() {
        showEmptyView()
    }

    private fun hideEmptyViw(){
        vpEmpty.visibility = View.GONE
    }

    override fun navigateToPatientCaseSummaryInfo(consultationrequestId: ConsultationRequestVO) {
        Log.d("specilaName",consultationrequestId.specialityName.toString())
        Log.d("specilaId",consultationrequestId.specialityId.toString())
        Log.d("id",consultationrequestId.id)
        startActivity(
            PatientInfoActivity.newIntent(
                this,
                consultationrequestId.id,
                consultationrequestId.specialityName.toString(),
                consultationrequestId.specialityId.toString()

            )

        )
    }

}