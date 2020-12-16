//package com.padc.the_health_care_app.adapters
//
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentStatePagerAdapter
//import com.padc.the_health_care_app.fragments.GeneralQuestionFragment
//import com.padc.the_health_care_app.fragments.SpecialQuestionFragment
//
//class PagerAdapter(fragmentManager: FragmentManager,speciality : String) : FragmentStatePagerAdapter(fragmentManager){
//    override fun getItem(position: Int): Fragment {
//        return when(position){
//            0 -> GeneralQuestionFragment.newInstance("A","B")
//            else -> SpecialQuestionFragment.newInstance("A","B")
//        }
//    }
//
//    override fun getCount(): Int {
//        return 2
//    }
//}