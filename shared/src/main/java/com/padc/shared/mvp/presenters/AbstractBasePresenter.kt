package com.padc.shared.mvp.presenters

import androidx.lifecycle.ViewModel
import com.padc.shared.mvp.views.BaseView

abstract class AbstractBasePresenter <T: BaseView> : BasePresenter<T>, BaseView,ViewModel() {
    var mView : T? = null

    override fun initPresenter(view: T) {
        mView = view
    }


}