package com.example.ricky.misoficios.Fragmentos


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.ricky.misoficios.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.frg_nuevooficio1.*


//class CreateFragment : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//        ): View? {
//            // Inflate the layout for this fragment
//            return inflater.inflate(R.layout.nuevo_oficio_constrained, container, false)
//    }
//
//
//}

class CreateFragment : Fragment()
{
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.frg_crear_oficio, container, false)
        return vista
    }


}

