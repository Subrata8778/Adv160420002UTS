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

class EditProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
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

        val txtName = view?.findViewById<TextView>(R.id.txtEditName)
        val txtAddress = view?.findViewById<TextView>(R.id.txtEditAddress)
        val txtPhone = view?.findViewById<TextView>(R.id.txtEditPhone)
        val txtEmail = view?.findViewById<TextView>(R.id.txtEditEmail)
        val btnUpdate = view.findViewById<Button>(R.id.btnUpdate)

        var name = shared.getString(NAME,"").toString()
        var address = shared.getString(ADDRESS,"").toString()
        var phone = shared.getString(PHONE,"").toString()
        var email = shared.getString(EMAIL,"").toString()

        txtName?.text = name
        txtAddress?.text = address
        txtPhone?.text = phone
        txtEmail?.text = email

        btnUpdate.setOnClickListener{
            var editor: SharedPreferences.Editor = shared.edit()
            editor.putString(NAME, txtName?.text.toString())
            editor.putString(ADDRESS, txtAddress?.text.toString())
            editor.putString(PHONE, txtPhone?.text.toString())
            editor.putString(EMAIL, txtEmail?.text.toString())
            editor.apply()

            val action = EditProfileFragmentDirections.actionItemProfile()
            Navigation.findNavController(it).navigate(action)
        }
    }
}