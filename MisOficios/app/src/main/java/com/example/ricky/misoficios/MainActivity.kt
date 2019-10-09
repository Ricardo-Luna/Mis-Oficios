package com.example.ricky.misoficios


import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.widget.Toast
import com.example.ricky.misoficios.Almacenado.DBHelper
import com.example.ricky.misoficios.Fragmentos.*
import com.example.ricky.misoficios.Modelos.Carpetas
import com.example.ricky.misoficios.Fragmentos.mostrarDocumento
import com.example.ricky.misoficios.Modelos.LoginRes
import com.example.ricky.misoficios.Modelos.Oficios
import com.example.ricky.misoficios.servicios.MisOficios
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import com.example.ricky.misoficios.servicios.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

//Temas:
//Azul: #3f4d60 , Gris: #72767c , Negro: #141216


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var carpetasRecycler: RecyclerView
    lateinit var carpetasList: ArrayList<Carpetas>
    lateinit var idrecibidos: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val globvar = MisOficios()


        val ax = globvar.getIDUsuario(this.application)
        // Log.d("XXXDOCUMENTOS: id: ", ax)


        //val axx = globvar.getCarpetaInicial(this.application)
        //Log.d("XXXDOCUMENTOS:carpeta: ", axx)

        val dbHandler = DBHelper(baseContext, null)
        val db = dbHandler.getCarpetaRecibidos()

        val cad = globvar.getIDUsuario(this.application)
        //  getCarpetaInicial(ax)
        //Log.d("XXXRESPONSECARPETA: ", "")

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.nwDoc)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Aquí se creará nuevo oficio", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
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

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //val inflater = menuInflater
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

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


    fun displayFragment(id: Int) {
        val fragment = when (id) {
            R.id.nav_home -> {
                MainFragment()

            }
            R.id.nav_grupos -> {
                MainFragment()

            }

            // R.id.nwDoc -> {
            //     //CrearOficio()
            // }
            R.id.itGrupo -> {
                NwGrupo()
            }


            else -> {
                MainFragment()
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.linearLayoutContentMain, fragment)
            .commit()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
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
//    toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary))
    //   var actionBar = supportActionBar
    //   //toolbar.setBackgroundColor("@drawable/gradient_blue")
    //   actionBar!!.title = "Recibidos"

}
