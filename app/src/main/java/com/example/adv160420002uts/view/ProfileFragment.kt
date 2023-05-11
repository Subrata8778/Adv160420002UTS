package com.example.adv160420002uts.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.adv160420002uts.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var NAME = "NAME"
        var ADDRESS = "ADDRESS"
        var PHONE = "PHONE"
        var EMAIL = "EMAIL"
        var STATUS = "STATUS"
        var sharedFile = "com.example.adv160420002uts"

        var shared: SharedPreferences = this.requireActivity().getSharedPreferences(sharedFile,
            Context.MODE_PRIVATE )

        val txtName = view.findViewById<TextView>(R.id.txtNameProfile)
        val txtAddress = view.findViewById<TextView>(R.id.txtAddress)
        val txtPhone = view.findViewById<TextView>(R.id.txtPhoneNumber)
        val txtEmail = view.findViewById<TextView>(R.id.txtEmail)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)
        val btnEdit = view.findViewById<Button>(R.id.btnEdit)

        var name = shared.getString(NAME,"").toString()
        var address = shared.getString(ADDRESS,"").toString()
        var phone = shared.getString(PHONE,"").toString()
        var email = shared.getString(EMAIL,"").toString()

        txtName.text = "Name : " + name
        txtAddress.text = "Address : " + address
        txtPhone.text = "Phone : " + phone
        txtEmail.text = "Email : " + email

        btnLogout.setOnClickListener{
            var editor: SharedPreferences.Editor = shared.edit()
            editor.putString(STATUS, "false")
            editor.apply()
            val action = ProfileFragmentDirections.actionProfileLogin()
            Navigation.findNavController(it).navigate(action)
        }
        btnEdit.setOnClickListener{
            val action = ProfileFragmentDirections.actionEditProfile()
            Navigation.findNavController(it).navigate(action)
        }
    }
}