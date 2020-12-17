package com.padc.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padc.doctor.R
import com.padc.doctor.delegates.QuestionDelegate
import com.padc.doctor.viewHolders.ShowQuestionViewHolder
import com.padc.doctor.viewHolders.SpecialQuestionPatientInfoViewHolder
import com.padc.shared.adapter.BaseRecyclerAdapter
import com.padc.shared.data.vos.SpecialQuestionVO

class ShowQuestionAdapter(private val delegate : QuestionDelegate) : BaseRecyclerAdapter<ShowQuestionViewHolder,SpecialQuestionVO>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowQuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_list_show_question,parent,false)
        return ShowQuestionViewHolder(view,delegate)
    }

}