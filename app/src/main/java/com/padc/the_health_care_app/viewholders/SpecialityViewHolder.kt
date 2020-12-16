package com.padc.the_health_care_app.viewholders

import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.padc.shared.data.vos.SpecialityVO
import com.padc.shared.viewholders.BaseViewHolder
import com.padc.the_health_care_app.delegates.SpecialityItemDelegate
import kotlinx.android.synthetic.main.rv_home_item.view.*

class SpecialityViewHolder(itemView: View, private val mDelegate : SpecialityItemDelegate) : BaseViewHolder<SpecialityVO>(itemView
) {

    override fun bindData(data: SpecialityVO) {
        mData = data
        itemView.tvSpecialityName.text = data.specialityName
        Glide.with(itemView.context)
            .load(data.photo)
            .into(itemView.ivSpeciality)
    }

    init {
        itemView.setOnClickListener {
            mData?.let {
                Log.d("key","tap on Speciality Item")
                mDelegate.onTapSpecialityItem(it)
            }

        }
    }


}
