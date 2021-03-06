package com.padc.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padc.doctor.R
import com.padc.doctor.delegates.ChatDelegate
import com.padc.doctor.utils.SessionManager
import com.padc.doctor.viewHolders.ChatPatientViewHolder
import com.padc.doctor.viewHolders.ChatViewDoctorViewHolder
import com.padc.shared.adapter.BaseRecyclerAdapter
import com.padc.shared.data.vos.ChatMessageVO
import com.padc.shared.viewholders.BaseViewHolder

class ChatAdapter (private val delegate: ChatDelegate) : BaseRecyclerAdapter<BaseViewHolder<ChatMessageVO>,ChatMessageVO>() {
    var doctorViewType = 1
    var patientViewType = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ChatMessageVO> {
        when (viewType) {
            doctorViewType -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.rv_item_chat_by_doctor, parent, false)
                return ChatViewDoctorViewHolder(view, delegate)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.rv_item_chat_by_patient, parent, false)
                return ChatPatientViewHolder(view, delegate)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        when {
            mData[position].sendBy == SessionManager.doctor_id.toString() -> {
                return doctorViewType
            }
            else -> {
                return patientViewType
            }
        }
    }
}
