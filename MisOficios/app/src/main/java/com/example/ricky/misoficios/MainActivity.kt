package com.example.ricky.misoficios


import android.content.Context
import android.content.Intent
import android.graphics.Color
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
import android.util.Log.d
import android.view.Menu
import android.widget.Toast
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.Fragmentos.*
import com.example.ricky.misoficios.Modelos.Carpetas
import com.example.ricky.misoficios.Modelos.folder
import com.example.ricky.misoficios.adaptador.AdapterCarpetas
import com.example.ricky.misoficios.adaptador.mostrarDocumento
import com.example.ricky.misoficios.servicios.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//Temas:
//Azul: #3f4d60 , Gris: #72767c , Negro: #141216


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var  carpetasRecycler: RecyclerView
    lateinit var carpetasList: ArrayList<Carpetas>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.setBackgroundColor(Color.parseColor("#3f4d60"))
        setSupportActionBar(toolbar)
        val fab: FloatingActionButton = findViewById(R.id.nwDoc)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Aquí se creará nuevo oficio", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            val intent = Intent(baseContext, mostrarDocumento::class.java)
            startActivity(intent)
        }
       // onMostrarCarpetas(this@MainActivity)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
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

    fun displayFragment(id: Int){
        val fragment = when (id)
        {
            R.id.nav_home -> {
                MainFragment()

            }
            R.id.nav_grupos -> {
                MainFragment()

            }

            R.id.nav_send -> {
                NwGrupo()
            }
            R.id.nwDoc ->
            {
                CrearOficio()
            }
            R.id.itGrupo ->
            {
                NwGrupo()
            }

           // R.id.itCarpeta ->
           // {
           //
           // }

            else -> {
                MainFragment()
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.linearLayoutContentMain, fragment).commit()
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

            R.id.recibidos -> {
                MainFragment()
            }

            R.id.nav_send -> {

                listaEmpleados()
            }

            R.id.borradores -> {
                MainFragment()
            }



            else ->
            {
                MainFragment()
            }
        }

        supportFragmentManager.beginTransaction().replace(R.id.linearLayoutContentMain, fragment).commit()


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
   // fun onMostrarCarpetas(context: Context){
   //     var usuario = SharedPreference.getInstance(context!!).usuario
   //     RetrofitClient.instance.getCarpetas("ae10550a-cf5c-4912-aed6-3b0adbcde508")
   //         .enqueue(object: Callback<List<folder>> {
//
   //             override fun onResponse(call: Call<List<folder>>, response: Response<List<folder>>) {
   //                 if(response.isSuccessful){
   //                     if (!response.body().isNullOrEmpty()) {
   //                         val folder = response.body()
   //                          d("Response recibido", "onResponse: ${response.body()!![0].Nombre}")
   //                         val adapter2 = AdapterCarpetas(buildCarpetas(folder!!))
   //                         carpetasRecycler.adapter = adapter2
   //                     }
   //                     else
   //                     {
   //                         d("onResponse: ","Vacio")
   //                     }
   //                 }
   //             }
   //             override fun onFailure(call: Call<List<folder>>, t: Throwable) {
   //                 Log.e("onFailure", t.message)
   //                 d("onFailure: ","No recibido")
   //             }
//
   //         })
   // }
    fun buildCarpetas(G: List<folder>): ArrayList<Carpetas>{
        carpetasList = ArrayList()
        for(item in G){
            carpetasList.add(
                Carpetas(
                    item.IdCarpeta,
                    item.IdUsuarioPropietario,
                    item.Nombre,
                    item.Recibidos,
                    item.Enviados,
                    item.Borradores,
                    item.IdUsuarioActualizacion,
                    item.FechaActualizacion
                )
            )
        }
        return  carpetasList
    }

}
