package com.example.ration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val host =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val sideBar = findViewById<NavigationView>(R.id.nav_view)
        sideBar.setupWithNavController(host.navController)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.addProductToDBFragment,
                R.id.fragmentDeleteProductFromDB,
                R.id.rationFragment,
                R.id.calculateFragment,
            ),
            drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout),
        )
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setupWithNavController(host.navController, appBarConfiguration)
    }
}