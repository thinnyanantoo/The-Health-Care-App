//package com.padc.the_health_care_app.fragments
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.AdapterView
//import androidx.lifecycle.ViewModelProviders
//import com.padc.the_health_care_app.R
//import com.padc.the_health_care_app.mvp.presenters.impls.PatientQuestionPresenterImpl
//import kotlinx.android.synthetic.main.fragment_general_question.*
//
//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [GeneralQuestionFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
//class GeneralQuestionFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_general_question, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setUpListener()
//    }
//
//    private var year : String? = ""
//    private var day : String? = ""
//    private var month : String? = ""
//    private var bloodType : String? = ""
//
//
//    private fun setUpListener() {
//        btnContinueInFirst.setOnClickListener {
//            etWeightInTwo.text = etAnswerWeight.text
//            etBloodWieghtInTwo.text = etAnswerBloodPressure.text
//            etwrongInSecond.text = etAnswerWrongMedicine.text
//            tvbdAnswerInSecond.text= "$year/$month/$day"
//            etheigtAnswerInSecond.text = etAnswerHeight.text
//            etbloodTypeAnswerInSecond.text = bloodType
//            lineaLayoutOne.visibility = View.GONE
//            linearLayoutTwo.visibility = View.VISIBLE
//        }
//
//        btnContinueInTwo.setOnClickListener {
//           SpecialQuestionFragment.newInstance("A","B")
//        }
//
//        btnContinueInTwo.setOnClickListener {
////            mModel.addOneTimeGeneralQuestionToPatient(tvbirthdayQuestionInSecond.text.toString(),tvbdAnswerInSecond.text.toString())
////            mModel.addOneTimeGeneralQuestionToPatient(tv)
//            linearLayoutTwo.visibility = View.GONE
//         //    LinearLayoutThree.visibility = View.VISIBLE
//        }
//
//        spinnerYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onItemSelected(
//                parent: AdapterView<*>,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                year = parent.getItemAtPosition(position).toString()
//            }
//
//        }
//
//        spinnerMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onItemSelected(
//                parent: AdapterView<*>,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                month = parent.getItemAtPosition(position).toString()
//            }
//
//        }
//
//        spinnerDay.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onItemSelected(
//                parent: AdapterView<*>,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                day = parent.getItemAtPosition(position).toString()
//            }
//
//        }
//        spinnerBloodType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onItemSelected(
//                parent: AdapterView<*>,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                bloodType = parent.getItemAtPosition(position).toString()
//            }
//
//        }
//
//    }
//
//
//    private fun setUpPresenter(){
//        mPresenter = ViewModelProviders.of(this).get(PatientQuestionPresenterImpl::class.java)
//        (mPresenter as PatientQuestionPresenterImpl).initPresenter(this)
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment GeneralQuestionFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            GeneralQuestionFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
//}