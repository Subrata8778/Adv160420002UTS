package com.example.adv160420002uts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.adv160420002uts.R
import com.example.adv160420002uts.util.loadImage
import com.example.adv160420002uts.viewmodel.DetailViewModelDoctor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class DoctorDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModelDoctor
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var doctorName = "";
        if(arguments != null) {
            doctorName =
                DoctorDetailFragmentArgs.fromBundle(requireArguments()).doctorName
        }

        viewModel = ViewModelProvider(this).get(DetailViewModelDoctor::class.java)
        viewModel.fetch(doctorName)
        observeViewModel()
    }
    fun observeViewModel(){
        viewModel.doctorLD.observe(viewLifecycleOwner, Observer {
            val txtDoctorName = view?.findViewById<TextView>(R.id.txtNameDetailDoctor)
            val txtSpecialist = view?.findViewById<TextView>(R.id.txtSpecialistDetail)
            val txtDesc = view?.findViewById<TextView>(R.id.txtDescDoctor)
            val imgDoc = view?.findViewById<ImageView>(R.id.imageViewDetailDoctor)
            var progressBarDoc = view?.findViewById<ProgressBar>(R.id.progressBarDetailDoctor)

            txtDoctorName?.text = viewModel.doctorLD.value?.name
            txtSpecialist?.text = viewModel.doctorLD.value?.specialist
            txtDesc?.text = viewModel.doctorLD.value?.desc
            if (progressBarDoc != null) {
                imgDoc?.loadImage(viewModel.doctorLD.value?.photoUrl, progressBarDoc)
            }
        })
    }
}