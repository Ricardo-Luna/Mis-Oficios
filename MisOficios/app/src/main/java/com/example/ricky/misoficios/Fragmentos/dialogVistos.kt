package com.example.ricky.misoficios.Fragmentos
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.example.ricky.misoficios.R
class dialogVistos(context: Context):Dialog(context) {
    init {
        setCancelable(false)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_views)
    }
}