package com.padc.the_health_care_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padc.shared.adapter.BaseRecyclerAdapter
import com.padc.shared.data.vos.SpecialityVO
import com.padc.the_health_care_app.R
import com.padc.the_health_care_app.delegates.SpecialityItemDelegate
import com.padc.the_health_care_app.viewholders.SpecialityViewHolder

class SpecialityAdapter(private var delegate: SpecialityItemDelegate) : BaseRecyclerAdapter<SpecialityViewHolder, SpecialityVO> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_home_item,parent,false)
        return SpecialityViewHolder(view,mDelegate = delegate)

    }

}