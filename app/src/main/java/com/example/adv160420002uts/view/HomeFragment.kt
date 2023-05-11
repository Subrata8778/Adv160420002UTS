package com.example.adv160420002uts.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.adv160420002uts.R

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var STATUS = "STATUS"
        var NAME = "NAME"

        var sharedFile = "com.example.adv160420002uts"

        var shared: SharedPreferences = this.requireActivity().getSharedPreferences(sharedFile,
            Context.MODE_PRIVATE )
        var statusLogin = shared.getString(STATUS,"").toString()

        if(statusLogin == "false"){
            val action = HomeFragmentDirections.actionLoginFragment()
            Navigation.findNavController(view).navigate(action)
        }

        val txtWelcome = view.findViewById<TextView>(R.id.txtWelcome)
        var name = shared.getString(NAME,"").toString()
        txtWelcome.text = "Welcome, " + name + ". Have a nice day !"
    }
}