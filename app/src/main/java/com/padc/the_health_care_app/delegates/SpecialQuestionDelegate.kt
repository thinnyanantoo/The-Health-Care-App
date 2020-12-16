package com.padc.the_health_care_app.delegates

import com.padc.shared.data.vos.CaseSummaryVO
import java.text.FieldPosition

interface SpecialQuestionDelegate {
    fun onAnswerChange(position: Int, caseSummaryVO: CaseSummaryVO)
}