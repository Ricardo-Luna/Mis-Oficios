package com.example.ricky.misoficios

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
import android.support.v7.widget.Toolbar
import android.view.Menu
import com.example.ricky.misoficios.Fragmentos.*
import io.reactivex.disposables.CompositeDisposable

//Temas:
//Azul: #3f4d60 , Gris: #72767c , Negro: #141216


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //loadData()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.setBackgroundColor(Color.parseColor("#3f4d60"))
        setSupportActionBar(toolbar)


        val fab: FloatingActionButton = findViewById(R.id.nwDoc)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Aquí se creará nuevo oficio", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            val intent = Intent(baseContext, NuevoOficio::class.java)
            startActivity(intent)
3
        }





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
        val inflater = menuInflater
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

}
