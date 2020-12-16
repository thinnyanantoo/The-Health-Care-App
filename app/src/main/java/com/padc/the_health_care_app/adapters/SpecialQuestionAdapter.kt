package com.padc.the_health_care_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.padc.shared.adapter.BaseRecyclerAdapter
import com.padc.shared.data.vos.CaseSummaryVO
import com.padc.shared.data.vos.SpecialQuestionVO
import com.padc.shared.data.vos.SpecialityVO
import com.padc.the_health_care_app.R
import com.padc.the_health_care_app.delegates.SpecialQuestionDelegate
import com.padc.the_health_care_app.delegates.SpecialityItemDelegate
import com.padc.the_health_care_app.mvp.views.PatientQuestionView
import com.padc.the_health_care_app.viewholders.SpecialQuestionViewHolder
import com.padc.the_health_care_app.viewholders.SpecialityViewHolder

class SpecialQuestionAdapter(private val mDelegate : SpecialQuestionDelegate) :
    BaseRecyclerAdapter<SpecialQuestionViewHolder, SpecialQuestionVO>() {

    var mCaseSummaryList : List<CaseSummaryVO> = arrayListOf()

    fun setCaseSummaryList(caseSummaryList : List<CaseSummaryVO>){
        mCaseSummaryList = caseSummaryList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialQuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_special_question,parent,false)
        return SpecialQuestionViewHolder(view,mCaseSummaryList, mDelegate)
    }
}
