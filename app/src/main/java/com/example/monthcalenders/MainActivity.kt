package com.example.monthcalenders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.monthcalenders.repo.Repo
import com.example.monthcalenders.viewModel.ApiViewModel
import com.example.monthcalenders.viewModel.ViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    lateinit var viewModel:ApiViewModel
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository= Repo()
        val viewModelProviderFactory=ViewModelProviderFactory(application as App,repository)
        viewModel= ViewModelProvider(this,viewModelProviderFactory)[ApiViewModel::class.java]
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_dashboard_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}