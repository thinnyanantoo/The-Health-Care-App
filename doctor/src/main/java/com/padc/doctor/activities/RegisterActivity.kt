package com.padc.doctor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import com.padc.doctor.R
import com.padc.doctor.mvp.presenter.RegisterPresenter
import com.padc.doctor.mvp.presenter.impls.RegisterPresenterImpl
import com.padc.doctor.mvp.views.RegisterView
import com.padc.shared.activity.BaseActivity
import com.padc.shared.data.vos.DoctorVO
import kotlinx.android.synthetic.main.activity_doctor_case_summary.*

class RegisterActivity : BaseActivity(), RegisterView {

    val doctorVO : DoctorVO ? = DoctorVO()

    var year = ""
    var month = ""
    var day = ""
    var speciality = ""
    var gender = ""
    var birthday = ""
    private lateinit var mPresenter: RegisterPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_case_summary)
        setUpPresenter()

        setUpListener()
        mPresenter.onUiReady(this)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, RegisterActivity::class.java)
            return intent

        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(RegisterPresenterImpl::class.java)
        mPresenter.initPresenter(this)

    }

    override fun navigateToLoginActivity() {
        startActivity(DoctorLoginActivity.newIntent(this))
    }


    private fun setUpListener() {

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
        spinnerGenderType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                gender = parent.getItemAtPosition(position).toString()
            }

        }

        spinnerSpecialities.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                speciality = parent.getItemAtPosition(position).toString()
            }

        }

        birthday = "$year/$month/$day"


        btnRegister.setOnClickListener {
            mPresenter.onTapRegister(
                email = etDoctorPhoneNumber.text.toString(),
                name = etDoctorName.text.toString(),
                password = etDoctorPassword.text.toString(),
                phoneNumber = etDoctorPhoneNumber.text.toString(),
                speciality = speciality.toString(),
                degree = etDegree.text.toString(),
                biography = etBio.text.toString(),
                experience = etExperience.text.toString(),
                DOB = "$year/$month/$day",
                photo = "",
                address = etAddress.text.toString())

            DoctorVO(
                id = etDoctorPhoneNumber.text.toString(),
                email = etDoctorPhoneNumber.text.toString(),
                name = etDoctorName.text.toString(),
                password = etDoctorPassword.text.toString(),
                phoneNumber = etDoctorPhoneNumber.text.toString(),
                specialityName = speciality.toString(),
                degree = etDegree.text.toString(),
                biography = etBio.text.toString(),
                experience = etExperience.text.toString(),
                DOB = "$year/$month/$day",
                photo = "",
                address = etAddress.text.toString())

        }
        }


    }