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
import com.google.gson.reflect.TypeToken

class ListViewModelDoctor(application: Application): AndroidViewModel(application) {
    val doctorLD = MutableLiveData<ArrayList<Doctor>>()
    val doctorLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue:RequestQueue? = null

    fun refresh() {
        loadingLD.value = true
        doctorLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/doctor.php?doctor_list"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {// kondisi sukses
                val sType = object : TypeToken<ArrayList<Doctor>>() { }.type
                val result = Gson().fromJson<ArrayList<Doctor>>(it, sType)
                doctorLD.value = result
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {// kondisi gagal
                Log.d("showvoley", it.toString())
                doctorLoadErrorLD.value = false
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)

//        val doctorList = "[{'name': 'Dr. John Smith',    'specialist': 'Cardiologist',    'location': 'New York',    'desc': 'Dr. John Smith is a highly experienced cardiologist with over 20 years of experience. He specializes in diagnosing and treating heart conditions.',    'photoUrl': 'https://example.com/doctors/john-smith.jpg'}," +
//                "{'name': 'Dr. Jane Doe',    'specialist': 'Neurologist',    'location': 'Los Angeles',    'desc': 'Dr. Jane Doe is a renowned neurologist who has been featured in several medical journals for her groundbreaking research on brain disorders.',    'photoUrl': 'https://example.com/doctors/jane-doe.jpg'}," +
//                "{'name': 'Dr. Michael Lee',    'specialist': 'Oncologist',    'location': 'Chicago',    'desc': 'Dr. Michael Lee is an expert in the field of oncology and has helped countless patients fight cancer. He is known for his compassionate and personalized approach to treatment.',    'photoUrl': 'https://example.com/doctors/michael-lee.jpg'}," +
//                "{'name': 'Dr. Sarah Jones',    'specialist': 'Pediatrician',    'location': 'Houston',    'desc': 'Dr. Sarah Jones is a pediatrician who has dedicated her career to helping children lead healthy and happy lives. She is known for her warm and friendly demeanor, which helps put her young patients at ease.',    'photoUrl': 'https://example.com/doctors/sarah-jones.jpg'}," +
//                "{'name': 'Dr. David Kim',    'specialist': 'Dermatologist',    'location': 'Miami',    'desc': 'Dr. David Kim is a skilled dermatologist who is passionate about helping his patients achieve healthy and beautiful skin. He is known for his expertise in the latest cosmetic treatments and procedures.',    'photoUrl': 'https://example.com/doctors/david-kim.jpg'}]"
//
//
//        val sType = object : TypeToken<ArrayList<Doctor>>() { }.type
//        val result = Gson().fromJson<ArrayList<Doctor>>(doctorList, sType)
//        doctorLD.value = result
//        loadingLD.value = false
//        doctorLoadErrorLD.value = false
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}
