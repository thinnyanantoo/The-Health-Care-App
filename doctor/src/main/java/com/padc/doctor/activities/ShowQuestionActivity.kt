package com.padc.doctor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.doctor.R
import com.padc.doctor.adapters.ShowQuestionAdapter
import com.padc.doctor.mvp.presenter.ShowSpecialQuestionPresenter
import com.padc.doctor.mvp.presenter.impls.ShowSpecialQuestionPresenterImpl
import com.padc.doctor.mvp.views.ShowSpecialQuestionView
import com.padc.shared.activity.BaseActivity
import com.padc.shared.data.vos.SpecialQuestionVO
import com.padc.shared.data.vos.SpecialityVO
import kotlinx.android.synthetic.main.activity_show_question.*

class ShowQuestionActivity : BaseActivity() ,ShowSpecialQuestionView{
    private lateinit var mPresenter: ShowSpecialQuestionPresenter
    private lateinit var mAdapter : ShowQuestionAdapter
    var name= ""
    var id= ""
    var cid = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_question)
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
        mPresenter = ViewModelProviders.of(this).get(ShowSpecialQuestionPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }


    private fun setUpAdapter(){
        mAdapter = ShowQuestionAdapter(mPresenter)
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvShowQuestion.layoutManager = linearLayoutManager
        rvShowQuestion.adapter = mAdapter

    }

    companion object {
        val SPECIALITYNAME = "SPECIALITYNAME"
        val SPECIALITYID = "SPECIALITYID"
        val CONSULTID = "CONSULTID"
        fun newIntent(context: Context,name : String,id : String,consultId : String): Intent {
            var intent = Intent(context,ShowQuestionActivity::class.java)
                intent.putExtra(SPECIALITYNAME,name)
                intent.putExtra(SPECIALITYID,id)
                intent.putExtra(CONSULTID, consultId)
            return intent
        }
    }

    override fun displaySpecialQuestion(question: List<SpecialQuestionVO>) {
        mAdapter.setNewData(question)
    }

    override fun navigateToChat() {
        startActivity(ChatActivity.newIntent(this,id,name,cid))
    }
}