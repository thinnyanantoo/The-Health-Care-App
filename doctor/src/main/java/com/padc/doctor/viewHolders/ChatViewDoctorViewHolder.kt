package com.padc.doctor.viewHolders

import android.view.View
import com.padc.doctor.delegates.ChatDelegate
import com.padc.shared.data.vos.ChatMessageVO
import com.padc.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.rv_item_chat_by_doctor.view.*

class ChatViewDoctorViewHolder(itemView: View, val delegate : ChatDelegate) : BaseViewHolder<ChatMessageVO>(itemView){
    override fun bindData(data: ChatMessageVO) {
        mData = data
        itemView.tvSendTime.text = data.sendAt.toString()
        itemView.tvMessageInDoctor.text = data.textMessage.toString()

    }

}