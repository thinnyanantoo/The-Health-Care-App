package com.padc.doctor.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.padc.doctor.delegates.QuestionDelegate
import com.padc.doctor.mvp.views.ShowSpecialQuestionView
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.SpecialityVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter
import com.padc.shared.mvp.presenters.BasePresenter

interface ShowSpecialQuestionPresenter : BasePresenter<ShowSpecialQuestionView> , QuestionDelegate{
    fun onUiReady(speicalityName : String,specialityId : String,consultId: String,lifecycleOwner: LifecycleOwner)

}
