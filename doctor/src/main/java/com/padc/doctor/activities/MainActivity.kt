package com.padc.doctor.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.padc.doctor.R
import com.padc.shared.activity.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener (OnCompleteListener { task ->
                if(!task.isSuccessful){
                    Log.e("TOKEN","getInstanceId Failed",task.exception)
                    return@OnCompleteListener
                }

                val token = task.result?.token
                val msg = "token $token"
                Log.e("TOKEN",msg)
                Toast.makeText(baseContext,msg,Toast.LENGTH_SHORT).show()
            })

    }
}
