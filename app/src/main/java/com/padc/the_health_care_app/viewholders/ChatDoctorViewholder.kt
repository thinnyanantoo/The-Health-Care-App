package com.padc.the_health_care_app.viewholders

import android.view.View
import com.padc.shared.data.vos.ChatMessageVO
import com.padc.shared.viewholders.BaseViewHolder
import com.padc.the_health_care_app.delegates.ChatDelegate
import kotlinx.android.synthetic.main.rv_item_chat_by_doctor.view.*

class ChatDoctorViewholder ( itemView: View) : BaseViewHolder<ChatMessageVO>(itemView) {
    override fun bindData(data: ChatMessageVO) {
        data?.let {
            itemView.tvMessageInDoctor?.text = it.textMessage
            itemView.tvSendTime?.text = it.sendAt
        } ?: "enter message"
    }

    override fun position(id: Long) {
        TODO("Not yet implemented")
    }
}