package com.padc.the_health_care_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padc.shared.adapter.BaseRecyclerAdapter
import com.padc.shared.data.vos.ChatMessageVO
import com.padc.shared.viewholders.BaseViewHolder
import com.padc.the_health_care_app.R
import com.padc.the_health_care_app.delegates.ChatDelegate
import com.padc.the_health_care_app.utils.SessionManager
import com.padc.the_health_care_app.viewholders.ChatDoctorViewholder
import com.padc.the_health_care_app.viewholders.ChatViewPatientViewHolder

class ChatHistoryAdapter () : BaseRecyclerAdapter<BaseViewHolder<ChatMessageVO>,ChatMessageVO>() {
    var doctorViewType = 1
    var patientViewType = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ChatMessageVO> {
        when (viewType) {
            doctorViewType-> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.rv_item_chat_by_patient, parent, false)
                return ChatViewPatientViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.rv_item_chat_by_doctor, parent, false)
                return ChatDoctorViewholder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        when {
            mData[position].sendBy == SessionManager.patient_id.toString() -> {
                return patientViewType
            }
            else -> {
                return doctorViewType
            }
        }
    }
}
