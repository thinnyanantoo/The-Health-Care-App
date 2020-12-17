package com.padc.doctor.viewHolders

import android.view.View
import androidx.appcompat.view.menu.ActionMenuItemView
import com.padc.doctor.delegates.QuestionDelegate
import com.padc.shared.data.vos.SpecialQuestionVO
import com.padc.shared.data.vos.SpecialityVO
import com.padc.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.rv_list_show_question.view.*

class ShowQuestionViewHolder(itemView: View,var delegate : QuestionDelegate) : BaseViewHolder<SpecialQuestionVO>(itemView){

    init {
        itemView.setOnClickListener {
            delegate.onTapQuestionItem()
        }
    }
    override fun bindData(data: SpecialQuestionVO) {
        mData = data
        itemView.itemShowQuestion.text = data.question
    }


}