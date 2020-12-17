package com.padc.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padc.doctor.R
import com.padc.doctor.delegates.RequestDelegate
import com.padc.doctor.viewHolders.RequestViewHolder
import com.padc.shared.adapter.BaseRecyclerAdapter
import com.padc.shared.data.vos.ConsultationRequestVO

class RequestAdapter(private val delegate : RequestDelegate) : BaseRecyclerAdapter<RequestViewHolder,ConsultationRequestVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.rv_request_list,parent,false)
            return RequestViewHolder(view,delegate)
        }
    }
