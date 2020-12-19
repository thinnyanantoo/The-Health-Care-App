package com.padc.the_health_care_app.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.padc.shared.activity.BaseActivity
import com.padc.the_health_care_app.R
import com.padc.the_health_care_app.fragments.HomeFragment
import com.padc.the_health_care_app.fragments.SecondFragment
import com.padc.the_health_care_app.fragments.ThirdFragment
import com.padc.the_health_care_app.mvp.presenters.MainPresenter
import com.padc.the_health_care_app.mvp.presenters.impls.MainPresenterImpl
import com.padc.the_health_care_app.mvp.views.MainView
import com.padc.the_health_care_app.utils.SessionManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() , MainView {
    private lateinit var mPresenter  : MainPresenter
    var patientId : String = ""
    var patientName : String = ""

    companion object {
        val PATIENTID = "PATIENTID"
        val PATIENTNAME = "PATIENTNAME"
        fun newIntent(context: Context,patientId : String,patientName : String): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(PATIENTID,patientId)
            intent.putExtra(PATIENTNAME,patientName)
                return intent
        }


        val CONSULTANTID = "CONSULTANTID"
        fun newIntentTwo(context: Context) : Intent {
            val intent = Intent(context,MainActivity::class.java)
           // intent.putExtra(CONSULTANTID)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpPresenter()

        patientId = intent.getStringExtra(PATIENTID).toString()
        patientName = intent.getStringExtra(PATIENTNAME).toString()


        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.frameLayout, HomeFragment.newInstance(patientId,patientName)
            )
                .commit()
        }

        bottomNavigation.setOnNavigationItemSelectedListener (
            object :
                BottomNavigationView.OnNavigationItemSelectedListener {
                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    when (item.itemId) {
                        R.id.bottomNavigation_home_menu -> {
                            supportFragmentManager.beginTransaction().replace(
                                R.id.frameLayout, HomeFragment.newInstance(patientId,patientName)
                            )
                                .commit()
                            return true
                        }
                        R.id.bottomNavigation_messenger_menu -> {
                            supportFragmentManager.beginTransaction().replace(
                                R.id.frameLayout, SecondFragment.newInstance("A", "B")
                            )
                                .commit()
                            return true

                        }
                        R.id.bottomNavigation_person_menu -> {
                            supportFragmentManager.beginTransaction().replace(
                                R.id.frameLayout, ThirdFragment.newInstance("A", "B")
                            )
                                .commit()
                            return true

                        }
                    }
                    return false
                }

            })
        Log.d("PatientidInHome",patientId)
        mPresenter.onUiReady(this,patientId)
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(MainPresenterImpl::class.java)
        (mPresenter as MainPresenterImpl).initPresenter(this)
    }

    override fun showView() {

    }


}