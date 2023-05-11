package com.example.adv160420002uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.adv160420002uts.model.Doctor
import com.google.gson.Gson

class DetailViewModelDoctor(application: Application): AndroidViewModel(application) {
    val doctorLD = MutableLiveData<Doctor>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    fun fetch(name : String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/doctor.php?name=" + name

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {// kondisi sukses
                val result = Gson().fromJson<Doctor>(it,
                    Doctor::class.java)
                doctorLD.value = result

                Log.d("showvoley", result.toString())
            },
            {// kondisi gagal
                Log.d("showvoley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}