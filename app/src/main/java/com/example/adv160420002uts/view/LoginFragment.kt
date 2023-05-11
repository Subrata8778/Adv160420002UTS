package com.example.adv160420002uts.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.adv160420002uts.R
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var USERNAME = "USERNAME"
        var PASSWORD = "PASSWORD"
        var NAME = "NAME"
        var ADDRESS = "ADDRESS"
        var PHONE = "PHONE"
        var EMAIL = "EMAIL"
        var STATUS = "STATUS"

        var sharedFile = "com.example.adv160420002uts"

        var shared: SharedPreferences = this.requireActivity().getSharedPreferences(sharedFile,
            Context.MODE_PRIVATE )

        var editor: SharedPreferences.Editor = shared.edit()
//        editor.putString(USERNAME, "s")
//        editor.putString(PASSWORD, "s")
//        editor.putString(NAME, "Subrata")
//        editor.putString(ADDRESS, "Jl. Rungkut Mejoyo Selatan")
//        editor.putString(PHONE, "081234567890")
//        editor.putString(EMAIL, "Subrata@gmail.com")
//        editor.apply()

        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val txtUsername = view.findViewById<EditText>(R.id.txtUsername)
        val txtPassword = view.findViewById<EditText>(R.id.txtPassword)

        btnLogin.setOnClickListener{
            var usernameLogin = shared.getString(USERNAME,"").toString()
            var passwordLogin = shared.getString(PASSWORD,"").toString()

            var inputUsername = txtUsername.text.toString()
            var inputPassword = txtPassword.text.toString()
            if(inputUsername == ""){
                Toast.makeText(this.context, "Username cannot be empty", Toast.LENGTH_SHORT).show()
            }else if (inputPassword == ""){
                Toast.makeText(this.context, "Password cannot be empty", Toast.LENGTH_SHORT).show()
            } else{
                if(inputUsername == usernameLogin && inputPassword == passwordLogin){
                    editor.putString(STATUS, "true")
                    editor.apply()

                    val action = LoginFragmentDirections.actionItemHome()
                    Navigation.findNavController(it).navigate(action)
                } else{
                    Toast.makeText(this.context, "Username or password incorrect!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}