package com.padc.doctor.viewHolders

import android.view.View
import com.padc.shared.data.vos.CaseSummaryVO
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.activity_patient_info.view.*
import kotlinx.android.synthetic.main.rv_item_list_question_specia.view.*

class SpecialQuestionPatientInfoViewHolder(itemView: View)  :  BaseViewHolder<CaseSummaryVO>(itemView){
    override fun bindData(data: CaseSummaryVO) {
         itemView.tvQuestion.text = data.question
        itemView.tvAnswer.text = data.answer

    }

}