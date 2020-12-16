package com.padc.the_health_care_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padc.shared.adapter.BaseRecyclerAdapter
import com.padc.shared.data.vos.CaseSummaryVO
import com.padc.the_health_care_app.R
import com.padc.the_health_care_app.viewholders.CaseSummaryViewHolder

class CaseSummaryAdapter : BaseRecyclerAdapter<CaseSummaryViewHolder,CaseSummaryVO>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaseSummaryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list_in_special_question_answer_in_confirm,parent,false)
        return CaseSummaryViewHolder(view)
    }
}