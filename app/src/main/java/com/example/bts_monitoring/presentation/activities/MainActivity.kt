package com.example.bts_monitoring.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bts_monitoring.R
import com.example.bts_monitoring.databinding.ActivityMainBinding
import com.example.bts_monitoring.presentation.service.MonitoringCarForegroundService
import com.example.bts_monitoring.presentation.service.MonitoringCarForegroundService.Companion.COMMAND_ID
import com.example.bts_monitoring.presentation.service.MonitoringCarForegroundService.Companion.COMMAND_START
import com.example.bts_monitoring.presentation.service.MonitoringCarForegroundService.Companion.COMMAND_STOP
import com.example.bts_monitoring.presentation.service.MonitoringService
import com.example.domain.models.TypeAppTheme
import com.example.domain.usecases.settings.GetTypeAppThemeUseCase
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MonitoringService {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var toolbar: MaterialToolbar

    @Inject lateinit var  getTypeAppThemeUseCase: GetTypeAppThemeUseCase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController =
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController

        setupToolBar()
        setupStatusBarColor()
        setupNavController()
        setupTypeAppTheme()
    }

    private fun setupTypeAppTheme() {
        if(getTypeAppThemeUseCase() == TypeAppTheme.DARK) {
            setDarkThemeApp(value = true)
        } else {
            setDarkThemeApp(value = false)
        }
    }

    fun setDarkThemeApp(value: Boolean) {
        if(value) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun setupStatusBarColor() {
        binding.appBarLayout.addLiftOnScrollListener { elevation, color ->
            window.statusBarColor = color
        }
    }

    private fun setupNavController() {
        binding.bottomMenu.setupWithNavController(navController)
        setupActionBarWithNavController(navController, getAppBarConfiguration())

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.zoneDetailsFragment, R.id.carBottomSheet -> binding.bottomMenu.visibility =
                    View.GONE
                else -> binding.bottomMenu.visibility = View.VISIBLE
            }
        }
    }

    private fun getAppBarConfiguration() = AppBarConfiguration(
        setOf(
            R.id.zoneFragment,
            R.id.statisticsFragment,
            R.id.settingsFragment
        )
    )

    private fun setupToolBar() {
        toolbar = binding.materialToolbar
        setSupportActionBar(toolbar)
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    private fun startMonitoringCarService() {
        val startIntent = Intent(this, MonitoringCarForegroundService::class.java)
        startIntent.putExtra(COMMAND_ID, COMMAND_START)
        startService(startIntent)
    }

    private fun stopMonitoringCarService() {
        val stopIntent = Intent(this, MonitoringCarForegroundService::class.java)
        stopIntent.putExtra(COMMAND_ID, COMMAND_STOP)
        startService(stopIntent)
    }

    override fun stopService() {
        stopMonitoringCarService()
    }

    override fun startService() {
        startMonitoringCarService()
    }
}