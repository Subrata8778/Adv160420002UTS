package com.example.adv160420002uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.adv160420002uts.model.Medicine
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModelMedicine(application: Application): AndroidViewModel(application) {
    val medicineLD = MutableLiveData<ArrayList<Medicine>>()
    val medicineLoadErrorLD = MutableLiveData<Boolean>()
    val medicineLoadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        medicineLoadingLD.value = true
        medicineLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/anmp/medicine.php?medicine_list"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {// kondisi sukses
                val sType = object : TypeToken<ArrayList<Medicine>>() { }.type
                val result = Gson().fromJson<ArrayList<Medicine>>(it, sType)
                medicineLD.value = result
                medicineLoadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {// kondisi gagal
                Log.d("showvoley", it.toString())
                medicineLoadErrorLD.value = false
                medicineLoadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)

//        val medicineList = "[{'name': 'Paracetamol',    'price': '10000',    'stock': '100',    'desc': 'Paracetamol is a commonly used pain reliever and fever reducer.',    'photoUrl': 'https://images.k24klik.com/product/apotek_online_k24klik_20210624013902359225_paracetamol-triman.jpg'}," +
//                "{'name': 'Ibuprofen',    'price': '12000',    'stock': '75',    'desc': 'Ibuprofen is a nonsteroidal anti-inflammatory drug (NSAID) used to relieve pain and reduce fever.',    'photoUrl': 'https://images.k24klik.com/product/large/apotek_online_k24klik_20211210093212359225_IBUPROFEN-TRIMAN-400MG-TAB-100S-removebg-preview.png'}," +
//                "{'name': 'Aspirin',    'price': '8000',    'stock': '50',    'desc': 'Aspirin is a medication used to treat pain, fever, or inflammation.',    'photoUrl': 'https://img2.beritasatu.com/cache/beritasatu/910x580-2/1623209335.jpg'}," +
//                "{'name': 'Acetaminophen',    'price': '9000',    'stock': '80',    'desc': 'Acetaminophen is a medication used to treat pain and fever.',    'photoUrl': 'https://www.drugwatch.com/wp-content/uploads/Acetaminophen.jpg'}," +
//                "{'name': 'Naproxe',    'price': '15000',    'stock': '60',    'desc': 'Naproxen is a nonsteroidal anti-inflammatory drug (NSAID) used to relieve pain and reduce inflammation.',    'photoUrl': 'https://www.saridon.com.ph/sites/g/files/vrxlpx39561/files/2020-10/Saridon%20Sarimax%20275%20Product%20%20720x520_01102020.png'}]"
//
//
//        val sType = object : TypeToken<ArrayList<Medicine>>() { }.type
//        val result = Gson().fromJson<ArrayList<Medicine>>(medicineList, sType)
//        medicineLD.value = result
//        medicineLoadErrorLD.value = false
//        medicineLoadingLD.value = false
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}