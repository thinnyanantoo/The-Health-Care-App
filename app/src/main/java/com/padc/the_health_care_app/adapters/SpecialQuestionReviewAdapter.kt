package com.padc.the_health_care_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padc.shared.adapter.BaseRecyclerAdapter
import com.padc.shared.data.vos.CaseSummaryVO
import com.padc.the_health_care_app.R
import com.padc.the_health_care_app.viewholders.SpecialQuestionReviewViewHolder

class SpecialQuestionReviewAdapter : BaseRecyclerAdapter<SpecialQuestionReviewViewHolder, CaseSummaryVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialQuestionReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_special_question_review,parent,false)
        return SpecialQuestionReviewViewHolder(view)
    }
}
