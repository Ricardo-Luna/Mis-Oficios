package com.example.ricky.misoficios


import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import com.example.ricky.misoficios.Almacenado.DBHelper
import com.example.ricky.misoficios.Fragmentos.*
import com.example.ricky.misoficios.Modelos.Carpetas
import com.example.ricky.misoficios.Fragmentos.mostrarDocumento
import com.example.ricky.misoficios.Modelos.Oficio
import com.example.ricky.misoficios.servicios.MisOficios
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import com.example.ricky.misoficios.servicios.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.lang.Exception

//Temas:
//Azul: #3f4d60 , Gris: #72767c , Negro: #141216

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbHandler = DBHelper(baseContext, null)
        val db = dbHandler.getCarpetaRecibidos()
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.nwDoc)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Aquí se creará nuevo oficio", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            toBeCalled()
            val intent = Intent(baseContext, mostrarDocumento::class.java)
            startActivity(intent)
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        displayFragment(-1)
    }

    override fun onCreateDialog(id: Int, args: Bundle?): Dialog? {
        return MainActivity().let {
            val builder = AlertDialog.Builder(baseContext)
            val inflater = dialogVistos(baseContext).layoutInflater;
            builder.setView(inflater.inflate(R.layout.dialog_views, null))
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
          //  R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun displayFragment(id: Int) {
        val fragment = when (id) {
            R.id.nav_home -> {
                MainFragment()
            }
            R.id.nav_grupos -> {
                MainFragment()
            }

            else -> {
                MainFragment()
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.linearLayoutContentMain, fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val fragment = when (item.itemId) {
            R.id.nav_home -> {
                MainFragment()
            }
            R.id.nav_grupos -> {
                GruposFrg()
            }

            else -> {
                MainFragment()
            }
        }

        supportFragmentManager.beginTransaction().replace(R.id.linearLayoutContentMain, fragment)
            .commit()

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun toBeCalled() {
        try {
            val intent = Intent(baseContext!!, mostrarDocumento::class.java)
            startActivity(intent)
        }catch (e: Exception)
        {
            println("Error: $e")
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------
}

//    toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary))
//   var actionBar = supportActionBar
//   //toolbar.setBackgroundColor("@drawable/gradient_blue")
//   actionBar!!.title = "Recibidos"

//  fun getCarpetaInicial(id: String) {
//      val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)
//
//      api.getCarpetaRecibidos(id)
//          .enqueue(object : Callback<List<Carpetas>> {
//              override fun onResponse(
//                  call: Call<List<Carpetas>>,
//                  response: Response<List<Carpetas>>
//              ) {
//                  if (response.isSuccessful) {
//                      val fold = response.body()!!
//                      //Log.d("XXXCARPETARECIBIDOS: ", fold[0].IdCarpeta.toString())
//                      idrecibidos = fold[0].IdCarpeta.toString()
//
//
//
//                      //Database block--------------------------------------
//                      val dbHandler = DBHelper(baseContext, null)
//                      dbHandler.addCarpetaRecibidos(fold[0].IdCarpeta.toString())
//                      val cursor = dbHandler.getCarpetaRecibidos()
//                      cursor!!.moveToLast()
//                      try {
//                          val str =
//                              cursor.getString(2)
//                                  .toString()
//                          Log.d("RESPONSERECIEVED: ", str)
//                      } catch (e: Exception) {
//                          Log.d("XXXEXCEPTION : ", e.toString())
//                      }
//                      ///////////////////////////////////////////////////////
//
//
//
//
//                  } else {
//                      Log.d("INGESU", "YA CHINGO ASU MARE COMARE")
//                  }
//              }
//
//              override fun onFailure(call: Call<List<Carpetas>>, t: Throwable) {
//                  Log.d("INGESU", " THIS AINT FUNNY ANYMORE")
//              }
//          })
//
//
//  }

// val fold = response.body()!!
// val dbHandler = DBHelper(this@MainActivity,null)
// dbHandler.addCarpetaRecibidos(fold.IdCarpeta.toString())
// val cursor = dbHandler.getCarpetaRecibidos()
// cursor!!.moveToFirst()
// ret = cursor.getString(0).toString()
// Log.d("XXXCARPETARECIBIDOS: ", cursor.getString(0).toString())