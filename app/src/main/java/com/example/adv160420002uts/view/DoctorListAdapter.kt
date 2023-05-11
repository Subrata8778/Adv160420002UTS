package com.example.adv160420002uts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.adv160420002uts.R
import com.example.adv160420002uts.model.Doctor
import com.example.adv160420002uts.util.loadImage

class DoctorListAdapter(val doctorList:ArrayList<Doctor>)
    : RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder>() {
    class DoctorViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            DoctorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.doctor_list_item, parent, false)
        return DoctorViewHolder(view)
    }

    override fun getItemCount(): Int {
        return doctorList.size
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val txtDoctorName = holder.view.findViewById<TextView>(R.id.txtDoctorName)
        val txtSpecialist = holder.view.findViewById<TextView>(R.id.txtSpecialist)
        val txtLoc = holder.view.findViewById<TextView>(R.id.txtLoc)
        val btnDetailDoctor = holder.view.findViewById<Button>(R.id.btnDetailDoctor)

        var doctorName = doctorList[position].name
        txtDoctorName.text = doctorName
        txtSpecialist.text = doctorList[position].specialist
        txtLoc.text = doctorList[position].location

        btnDetailDoctor.setOnClickListener {
            val action = DoctorFragmentDirections.actionDoctorDetailFragment(doctorName.toString())
            Navigation.findNavController(it).navigate(action)
        }

        var imageViewDoctor = holder.view.findViewById<ImageView>(R.id.imageViewDoctor)
        var progressBarDoctor = holder.view.findViewById<ProgressBar>(R.id.progressBarDoctor)
        imageViewDoctor.loadImage(doctorList[position].photoUrl, progressBarDoctor)

    }

    fun updateDoctorList(newDoctorList: ArrayList<Doctor>) {
        doctorList.clear()
        doctorList.addAll(newDoctorList)
        notifyDataSetChanged()
    }
}