package com.padc.the_health_care_app.viewholders

import android.view.View
import com.padc.shared.data.vos.CaseSummaryVO
import com.padc.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.rv_special_question_review.view.*

class SpecialQuestionReviewViewHolder (itemView: View)  :  BaseViewHolder<CaseSummaryVO>(itemView){
    override fun bindData(data: CaseSummaryVO) {
        itemView.tvQuestion.text = data.question
        itemView.tvAnswer.text = data.answer

    }

    override fun position(id: Long) {
        TODO("Not yet implemented")
    }

}