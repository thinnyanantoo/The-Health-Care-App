package com.padc.the_health_care_app.viewholders

import android.util.Log
import android.view.View
import com.padc.shared.data.vos.CaseSummaryVO

import com.padc.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_list_in_special_question_answer_in_confirm.view.*

class CaseSummaryViewHolder (itemView: View) : BaseViewHolder<CaseSummaryVO>(itemView){
    override fun bindData(data: CaseSummaryVO) {
        data?.let {
            itemView.tvQuestion.text = "(${adapterPosition+1}\t" + data.question
            itemView.tvAnswer.text = data.answer
            data.answer?.let { it1 -> Log.d("VANS", it1) }
        }
    }

    override fun position(id: Long) {
        TODO("Not yet implemented")
    }

}