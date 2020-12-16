package com.padc.shared.mvp.presenters

import com.padc.shared.mvp.views.BaseView

interface BasePresenter<T : BaseView> {
    fun initPresenter(view : T)
}