package com.padc.the_health_care_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padc.shared.adapter.BaseRecyclerAdapter
import com.padc.shared.data.vos.AddressVO
import com.padc.shared.viewholders.BaseViewHolder
import com.padc.the_health_care_app.R
import com.padc.the_health_care_app.delegates.PatietnAddressItemDelegate
import com.padc.the_health_care_app.viewholders.PatientAddressViewHolder

class PatientAddressAdapter(delegate: PatietnAddressItemDelegate, var previousPosition: Int) :
    BaseRecyclerAdapter<PatientAddressViewHolder, AddressVO>() {
    val mDelegate = delegate

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientAddressViewHolder {

        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_list_item_address, parent, false)
        return PatientAddressViewHolder(view,previousPosition, mDelegate)
    }
}