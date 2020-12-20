package com.padc.the_health_care_app.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.data.vos.DoctorVO
import com.padc.shared.data.vos.SpecialityVO
import com.padc.shared.fragments.BaseFragment
import com.padc.the_health_care_app.R
import com.padc.the_health_care_app.activities.ChatPatientActivity
import com.padc.the_health_care_app.activities.MainActivity.Companion.PATIENTID
import com.padc.the_health_care_app.activities.MainActivity.Companion.PATIENTNAME
import com.padc.the_health_care_app.activities.PatientInfoFillingForm
import com.padc.the_health_care_app.adapters.RecentlyAdapter
import com.padc.the_health_care_app.adapters.SpecialityAdapter
import com.padc.the_health_care_app.mvp.presenters.SpecialityPresenter
import com.padc.the_health_care_app.mvp.presenters.impls.SpecialityPresenterImpl
import com.padc.the_health_care_app.mvp.views.SpecialityView
import com.padc.the_health_care_app.utils.SessionManager
import kotlinx.android.synthetic.main.fragment_confirm_consultation_dialog.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_recently_doctor.*
import kotlinx.android.synthetic.main.layout_start_consultation_confirm_from_doctor.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment(), SpecialityView {

    private lateinit var mAdapter: SpecialityAdapter
    private lateinit var mPresenter: SpecialityPresenter
    private lateinit var mRecentlyAdapter: RecentlyAdapter
    var patientId = ""
    var patientName = ""

    var consultationId = ""
    var requestAcceptyId = ""

    var consultationrequestVO: ConsultationRequestVO? = null


    // TODO: Rename and change types of parameters
    private var idOfPatient: String? = ""
    private var nameOfPatient: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idOfPatient = it.getString(PATIENTID)
            nameOfPatient = it.getString(PATIENTNAME)
        }

        Log.d("pIdInHome", idOfPatient.toString())
        Log.d("pNameInHome", nameOfPatient.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        setUpRecyclerView()

        setUpListener()


        patientId = idOfPatient.toString()
        patientName = nameOfPatient.toString()

        SessionManager.patient_id = patientId


        hideConsultationReceived()
        Log.d("PatientIdInHomeSecond", patientId)
        Log.d("PatientNameInHomeSecond", patientName)


        mPresenter.onUiReady(this, patientId)
        mPresenter.onUiReadyForConsultatinConfrim(this)
    }

    private fun setUpRecyclerView() {
        mAdapter = SpecialityAdapter(mPresenter)
        rvHome.adapter = mAdapter
        rvHome.layoutManager = GridLayoutManager(requireContext(), 2)

        mRecentlyAdapter = RecentlyAdapter()
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvRecentlyDoctor.adapter = mRecentlyAdapter
        rvRecentlyDoctor.layoutManager = linearLayoutManager
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(activity!!).get(SpecialityPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpListener() {
        //   btnStartConsult.setOnClickListener {
        //    mPresenter.onTapStartConsultation(consultationrequestVO!!.id, consultationrequestVO!!)
        //    startActivity(activity?.let { it1 -> ChatPatientActivity.newIntent(it1, consultationId) })


        btnStartConsult.setOnClickListener {
            SessionManager.request_id_for_patient?.let { it1 ->
                mPresenter.onTapStartConsultation(
                    it1,
                    toString(),
                    SessionManager.patient_id.toString()
                )
            }
        }
    }


    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(id: String, name: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(PATIENTID, id)
                    putString(PATIENTNAME, name)
                }
            }
    }

    override fun displaySpecialityList(speciality: List<SpecialityVO>) {
        mAdapter.setNewData(speciality.toMutableList())
    }

    override fun displayRecentlyDoctor(doctor: DoctorVO) {
        TODO("Not yet implemented")
    }

    override fun showError(message: String) {
        TODO("Not yet implemented")
    }

    override fun displayFormToFillPatientInfo(specialityVO: SpecialityVO, patientId: String) {
        context?.let {
            startActivity(
                PatientInfoFillingForm.newIntent(
                    it,
                    specialityVO.id,
                    specialityVO.specialityName,
                    patientId,
                    patientName
                )
            )
        }
        Log.d("SpecialityId ", specialityVO.id)

        }


    override fun showDialog(specialityVO: SpecialityVO) {
        val view = layoutInflater.inflate(R.layout.fragment_confirm_consultation_dialog, null)
        val dialog = context?.let { Dialog(it) }
        val name = view?.findViewById<TextView>(R.id.specialityNameInDialog)
        name?.let {
            name
            name.text =
                specialityVO?.specialityName + resources.getString(R.string.text_speciality_name)
        }

        dialog?.apply {
            setContentView(view)
        }

        view.btnCancelDialog.setOnClickListener {
            dialog?.dismiss()
        }

        view.btnSureDialog.setOnClickListener {
            context?.let {
                startActivity(
                    PatientInfoFillingForm.newIntent(
                        it,
                        specialityVO.id,
                        specialityVO.specialityName,
                        patientId = patientId,
                        patientName = patientName
                    )
                )
            }
            dialog?.dismiss()
        }
        dialog?.show()
    }

    override fun showConsultationRequestReceived(consultation: ConsultationRequestVO) {

        tvDoctorName.text = consultation.doctorVO?.name
        tvDoctorSpeciality.text = consultation.specialityName
        tvHistory.text = consultation.doctorVO?.name+  getString(R.string.varb)+ consultation.doctorVO?.biography

        consultationId = consultation.id.toString()
        consMessage.text =
            getString(R.string.consultatioin_received) + consultation.doctorVO?.name + getString(R.string.consultatioin_receivedTwo)
        confirmConsultationlayout.visibility = View.VISIBLE

    }


    override fun navigateToChartActivity(
        consultationId: String
    ) {
        Log.d("consultationId", consultationId)
        startActivity(ChatPatientActivity.newIntent(requireContext(), SessionManager.request_id_for_patient.toString()))
    }


    private fun hideConsultationReceived(){
         confirmConsultationlayout.visibility = View.GONE
    }
}
