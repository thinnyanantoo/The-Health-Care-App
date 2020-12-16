package com.padc.doctor.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.padc.doctor.mvp.views.ShowSpecialQuestionView
import com.padc.shared.data.vos.SpecialityVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter

interface ShowSpecialQuestionPresenter{
    fun onUiReady(specialityVO: SpecialityVO,lifecycleOwner: LifecycleOwner)
}
