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
import com.example.adv160420002uts.model.Medicine
import com.example.adv160420002uts.util.loadImage

class MedicineListAdapter(val medicineList:ArrayList<Medicine>)
    : RecyclerView.Adapter<MedicineListAdapter.MedicineViewHolder>() {
    class MedicineViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MedicineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.medicine_list_item, parent, false)
        return MedicineViewHolder(view)
    }

    override fun getItemCount(): Int {
        return medicineList.size
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val txtMedicineName = holder.view.findViewById<TextView>(R.id.txtMedicineName)
        val txtPrice = holder.view.findViewById<TextView>(R.id.txtPrice)
        val txtStock = holder.view.findViewById<TextView>(R.id.txtStock)
        val btnDetailMedicine = holder.view.findViewById<Button>(R.id.btnDetailMedicine)

        var medicineName = medicineList[position].name
        txtMedicineName.text = medicineName
        txtPrice.text = "Price : Rp. " + medicineList[position].price.toString()
        txtStock.text = "Stock : " + medicineList[position].stock.toString()

        btnDetailMedicine.setOnClickListener {
            val action = MedicineFragmentDirections.actionMedicineDetailFragment(medicineName.toString())
            Navigation.findNavController(it).navigate(action)
        }

        var imageViewMedicine = holder.view.findViewById<ImageView>(R.id.imageViewMedicine)
        var progressBarMedicine = holder.view.findViewById<ProgressBar>(R.id.progressBarMedicine)
        imageViewMedicine.loadImage(medicineList[position].photoUrl, progressBarMedicine)

    }

    fun updateMedicineList(newMedicineList: ArrayList<Medicine>) {
        medicineList.clear()
        medicineList.addAll(newMedicineList)
        notifyDataSetChanged()
    }
}