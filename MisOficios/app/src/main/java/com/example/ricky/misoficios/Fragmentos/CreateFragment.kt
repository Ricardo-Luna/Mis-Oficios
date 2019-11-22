package com.example.ricky.misoficios.Fragmentos



import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ricky.misoficios.R

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

