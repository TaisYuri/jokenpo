package com.example.navigationcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.navigationcomponent.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView

    var selectedPlay: String = "Papel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNav = binding.bottomNav
        supportActionBar?.setTitle("Jokenpô")

        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHost.navController

        bottomNav.setupWithNavController(navController)

        bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.resultFragment -> {
                    val args = Bundle()
                    args.putString("selectPlay", selectedPlay)
                    navController.navigate(it.itemId, args)
                }
                else -> navController.navigate(it.itemId)
            }
            true
        }
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val availablePlays = resources.getStringArray(R.array.available_plays_array)
        selectedPlay = availablePlays[position]

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d("jokenpô","nenhum item selecionado")
    }

}
