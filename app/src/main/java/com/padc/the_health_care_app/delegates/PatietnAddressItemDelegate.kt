package com.padc.the_health_care_app.delegates

import com.padc.shared.data.vos.AddressVO

interface PatietnAddressItemDelegate {
    fun onTapAddress(address: AddressVO, previousPosition : Int)

    fun showEmptyAddressView()
    fun showRecyclerAddresView()

}