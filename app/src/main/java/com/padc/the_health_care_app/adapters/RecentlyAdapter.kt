package com.padc.the_health_care_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.shared.adapter.BaseRecyclerAdapter
import com.padc.shared.data.vos.RecentlyDoctorVo
import com.padc.the_health_care_app.R
import com.padc.the_health_care_app.viewholders.RecentlyViewHolder
import com.padc.the_health_care_app.viewholders.SpecialityViewHolder

class RecentlyAdapter : RecyclerView.Adapter<RecentlyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentlyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_recently_doctor,parent,false)
        return RecentlyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: RecentlyViewHolder, position: Int) {
    }
}