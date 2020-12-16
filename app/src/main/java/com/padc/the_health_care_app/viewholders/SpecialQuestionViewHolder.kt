package com.padc.the_health_care_app.viewholders


import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.padc.shared.data.vos.CaseSummaryVO
import com.padc.shared.data.vos.SpecialQuestionVO

import com.padc.shared.viewholders.BaseViewHolder
import com.padc.the_health_care_app.delegates.SpecialQuestionDelegate
import kotlinx.android.synthetic.main.item_list_in_special_question_answer_in_confirm.view.*
import kotlinx.android.synthetic.main.item_special_question.view.*
import org.w3c.dom.Text


class SpecialQuestionViewHolder (itemView: View, var mCaseSummaryList : List<CaseSummaryVO>,var mDelegate : SpecialQuestionDelegate) : BaseViewHolder<SpecialQuestionVO>(itemView) {
    override fun bindData(data: SpecialQuestionVO) {
        mData = data
        itemView.tvSpecialQuestion.text = "(${adapterPosition+1}) ${data.question}"

        mCaseSummaryList.let {
            itemView.etAnswer.text = Editable.Factory.getInstance().newEditable(mCaseSummaryList[adapterPosition].answer)
        }


        itemView.etAnswer.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
                var caseSummaryVO= CaseSummaryVO(data.id,data.question,itemView.etAnswer.text.toString())
                mDelegate.onAnswerChange(adapterPosition,caseSummaryVO)
            }

            override fun afterTextChanged(editable: Editable) {
                var caseSummaryVO= CaseSummaryVO(data.id,data.question,itemView.etAnswer.text.toString())
                mDelegate.onAnswerChange(adapterPosition,caseSummaryVO)
            }
        })

//        itemView.etAnswer.addTextChangedListener(object  : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                val caseSummaryVO = CaseSummaryVO(data.id,data.question,itemView.etAnswer.text.toString())
//                mDelegate.onAnswerChange(adapterPosition,caseSummaryVO)
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//        })
    }
}
