package com.padc.the_health_care_app.delegates

import com.padc.shared.data.vos.SpecialityVO

interface SpecialityItemDelegate{
    fun onTapSpecialityItem(specialityVO: SpecialityVO)
}