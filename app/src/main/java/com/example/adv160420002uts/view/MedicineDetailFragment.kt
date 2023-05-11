package com.example.adv160420002uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.adv160420002uts.R
import com.example.adv160420002uts.util.loadImage
import com.example.adv160420002uts.viewmodel.DetailViewModelDoctor
import com.example.adv160420002uts.viewmodel.DetailViewModelMedicine

class MedicineDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModelMedicine
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicine_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var medicineName = "";
        if(arguments != null) {
            medicineName =
                MedicineDetailFragmentArgs.fromBundle(requireArguments()).medicineName
        }

        viewModel = ViewModelProvider(this).get(DetailViewModelMedicine::class.java)
        viewModel.fetch(medicineName)
        observeViewModel()
    }
    fun observeViewModel(){
        viewModel.medicineLD.observe(viewLifecycleOwner, Observer {
            val txtMedicineName = view?.findViewById<TextView>(R.id.txtNameDetailMedicine)
            val txtDesc = view?.findViewById<TextView>(R.id.txtDescMedicine)
            val imgMed = view?.findViewById<ImageView>(R.id.imageViewDetailMedicine)
            var progressBarMed = view?.findViewById<ProgressBar>(R.id.progressBarDetailMedicine)

            txtMedicineName?.text = viewModel.medicineLD.value?.name
            txtDesc?.text = viewModel.medicineLD.value?.desc
            if (progressBarMed != null) {
                imgMed?.loadImage(viewModel.medicineLD.value?.photoUrl, progressBarMed)
            }
        })
    }
}