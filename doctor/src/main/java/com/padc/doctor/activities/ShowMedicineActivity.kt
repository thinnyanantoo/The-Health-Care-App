package com.padc.doctor.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.doctor.R
import com.padc.doctor.activities.ChatActivity.Companion.SPECIALITYID
import com.padc.doctor.activities.ChatActivity.Companion.SPECIALITYNAME
import com.padc.doctor.activities.ShowQuestionActivity.Companion.CONSULTID
import com.padc.doctor.adapters.ShowMedicineAdapter
import com.padc.doctor.adapters.ShowQuestionAdapter
import com.padc.doctor.mvp.presenter.PrescriptionMedicinePresenter
import com.padc.doctor.mvp.presenter.ShowSpecialQuestionPresenter
import com.padc.doctor.mvp.presenter.impls.PrescriptionMedicinePresenterImpl
import com.padc.doctor.mvp.presenter.impls.ShowSpecialQuestionPresenterImpl
import com.padc.doctor.mvp.views.PrescriptionMedicineView
import com.padc.doctor.mvp.views.ShowSpecialQuestionView
import com.padc.doctor.utils.SessionManager
import com.padc.shared.activity.BaseActivity
import com.padc.shared.data.vos.*
import kotlinx.android.synthetic.main.activity_show_medicine.*
import kotlinx.android.synthetic.main.activity_show_question.*
import kotlinx.android.synthetic.main.prescribe_medicine_routine_dialog.view.*
import java.util.*
import java.util.Locale.filter
import kotlin.collections.ArrayList

class ShowMedicineActivity : BaseActivity(), PrescriptionMedicineView {
    private lateinit var mPresenter: PrescriptionMedicinePresenter
    private lateinit var mAdapter: ShowMedicineAdapter
    var name = ""
    var id = ""
    var cid = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_medicine)
            setUpPresenter()
            setUpAdapter()
            setUpListener()
        name = intent.getStringExtra(SPECIALITYNAME).toString()
        id = intent.getStringExtra(SPECIALITYID).toString()
        cid = intent.getStringExtra(CONSULTID).toString()
        mPresenter.onUiReady(name, id, cid, this)
        Log.d("SSSID", id)
        Log.d("SSSNAME", name)
        Log.d("CCCCIDD", cid)
    }

        private fun setUpPresenter(){
            mPresenter = ViewModelProviders.of(this).get(PrescriptionMedicinePresenterImpl::class.java)
            mPresenter.initPresenter(this)
        }


        private fun setUpAdapter(){
            mAdapter = ShowMedicineAdapter(mPresenter)
            val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            rvShowMedicine.layoutManager = linearLayoutManager
            rvShowMedicine.adapter = mAdapter

        }

    companion object {
        val SPECIALITYNAME = "SPECIALITYNAME"
        val SPECIALITYID = "SPECIALITYID"
        val CONSULTID = "CONSULTID"
        fun newIntent(context: Context, name: String, id: String, consultId: String): Intent {
           var intent = Intent(context, ShowMedicineActivity::class.java)
            intent.putExtra(SPECIALITYNAME, name)
            intent.putExtra(SPECIALITYID, id)
            intent.putExtra(CONSULTID, consultId)
            return intent
        }
    }

    override fun showPrescriptionMedicine(medicine: List<MedicineVO>) {
        this.list = medicine
        mAdapter.setNewData(medicine)
    }

    override fun showPrescribeMedicineDialog(medicineVO: MedicineVO) {
        var morningstatus = true
        var afternoonstatus = true
        var nightstatus = true

        var number = 1
        var daycount: Int = 0
        var tabcount: String = "1"
        var eatingtime: String = ""
        var daystemp: String = ""
        var count = 0

        val view = layoutInflater.inflate(R.layout.prescribe_medicine_routine_dialog, null)
        val tabAccount = view?.findViewById<TextView>(R.id.tv_tabcount)
        val comment = view?.findViewById<EditText>(R.id.tv_comment)


        val dialog = this?.let { Dialog(it) }

        dialog?.apply {
            setCancelable(true)
            setContentView(view)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        view.txt_medicine_name.text = medicineVO.mname
        tabAccount?.text = tabcount


        view.tv_morning.setOnClickListener {

            morningstatus = if (morningstatus) {
                view.tv_morning.setBackgroundResource(R.drawable.round_btn_blue)
                view.tv_morning.setTextColor(Color.WHITE)
                count++
                false
            } else {
                view.tv_morning.setBackgroundResource(R.drawable.round_btn)
                view.tv_morning.setTextColor(Color.BLACK)
                count--
                true
            }

            if (count > -1) {
                var result = number * daycount * count
                tabAccount?.text = result.toString()
                tabcount = result.toString()
            }
        }

        view.tv_afternoon.setOnClickListener {
            afternoonstatus = if(afternoonstatus) {
                view.tv_afternoon.setBackgroundResource(R.drawable.round_btn_blue)
                view.tv_afternoon.setTextColor(Color.WHITE)
                count++
                false
            }else{
                view.tv_afternoon.setBackgroundResource(R.drawable.round_btn)
                view.tv_afternoon.setTextColor(Color.BLACK)
                count--
                true
            }
            if(count > -1)
            {
                var result = number * daycount * count
                tabAccount?.text = result.toString()
                tabcount = result.toString()
            }
        }

        view.tv_night.setOnClickListener {
            nightstatus = if(nightstatus) {
                view.tv_night.setBackgroundResource(R.drawable.round_btn_blue)
                view.tv_night.setTextColor(Color.WHITE)
                count++
                false
            }else{
                view.tv_night.setBackgroundResource(R.drawable.round_btn)
                view.tv_night.setTextColor(Color.BLACK)
                count--
                true
            }
            if(count > -1)
            {
                var result = number * daycount * count
                tabAccount?.text = result.toString()
                tabcount = result.toString()
            }
        }
        view.tv_before_eating.setOnClickListener {
            view.tv_before_eating.setBackgroundResource(R.drawable.round_btn_blue)
            view.tv_before_eating.setTextColor(Color.WHITE)
            view.tv_after_eating.setBackgroundResource(R.drawable.round_btn)
            view.tv_after_eating.setTextColor(Color.BLACK)
            eatingtime= "အစားမစားမှီသောက်ရန်"
        }

        view.tv_after_eating.setOnClickListener {
            view.tv_after_eating.setBackgroundResource(R.drawable.round_btn_blue)
            view.tv_after_eating.setTextColor(Color.WHITE)
            view.tv_before_eating.setBackgroundResource(R.drawable.round_btn)
            view.tv_before_eating.setTextColor(Color.BLACK)
            eatingtime = "အစားစားပြီးမှ သောက်ရန်"
        }

        view.day_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                var day = parent.getItemAtPosition(position).toString()
                if(day == "Days")
                {
                    daycount =1
                    daystemp= " Days"
                }else{
                    daycount = 7
                    daystemp=" Week"
                }

                var result = number * daycount * count
                tabAccount?.text = result.toString()
                tabcount = result.toString()

            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        view.ed_day.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                var data= s.toString()
                if(data.isNotEmpty())
                {
                    number = data.toInt()
                    var result = number * daycount * count
                    view.tv_tabcount.text = result.toString()
                    tabcount = result.toString()
                }
            }
        })

        view.confirm.setOnClickListener {
            // prescription list add
            var days : String =""
            if(morningstatus)
            {
                days += " မနက် ၊ "
            }
            if(afternoonstatus)
            {
                days += "နေ့  ၊  "
            }
            if(nightstatus)
            {
                days += "ည"
            }

            var medicaltime : String =""
            val routineLists: ArrayList<RoutineVO> = arrayListOf()

            var routineVO = RoutineVO(
                id= "0",
                amount = medicineVO.price.toString(),
                days = view.ed_day.text.toString() + daystemp,
                comment = comment?.text.toString(),
                quantity = tabcount,
                times = days,
                repeat = eatingtime
            )
            routineLists.add(routineVO)

            var prescriptionVO = PresriptionVO(
                id = UUID.randomUUID().toString(),
                mname= medicineVO.mname.toString(),
                price =  medicineVO.price.toString(),
                count = tabcount,
                routine = routineLists

            )
            if(comment?.text.toString().isNotEmpty()) {
                mPresenter.addToPrescribeMedicineList(
                    prescriptionVO)
                dialog?.dismiss()
            }else{
                Toast.makeText(this,"Please enter all field",Toast.LENGTH_SHORT).show()
            }
        }

        dialog?.show()

    }


    override fun showtext(text: String) {
        TODO("Not yet implemented")
    }

    override fun navigateToSplashScreen() {
        TODO("Not yet implemented")
    }

    private  var filterlist : ArrayList<MedicineVO> = arrayListOf()
    private lateinit var list : List<MedicineVO>
    private val medicineCountLists = mutableListOf<String>()
    private val medicineTypeLists = mutableListOf<String>()

    private var prescriptionLists: ArrayList<PresriptionVO> = arrayListOf()

    private lateinit var mConsultationChatVO: ConsultationVO

    private fun setUpListener(){
        ed_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
        })

        btn_prescribeAndStop.setOnClickListener {
            val consultationVO = ConsultationVO(
                mConsultationChatVO.doctor,mConsultationChatVO.patient,SessionManager.con_id.toString(),mConsultationChatVO.chatMessage
            )

            mPresenter.onTapStopConsultation(consultationVO)
        }
    }

    fun filter(text : String) {
        filterlist.clear()
        list?.let{
            for(item in list){
                var st = item.mname.toString().toLowerCase()
                if(st.contains(text))
                {
                    filterlist.add(item)
                }
            }
            mAdapter.setNewData(filterlist)
        }
    }

    override fun displayPatientRequestData(data: ConsultationVO) {
        mConsultationChatVO = data
    }

    override fun displayPrescriptionLists(prescriptionVO: PresriptionVO) {
        Log.d("plists", prescriptionLists.size.toString())
    }

    override fun navigatetoChatScreen() {
        startActivity(ChatActivity.newIntent(this,SessionManager.con_id.toString(),name,id))
        finish()
    }


}