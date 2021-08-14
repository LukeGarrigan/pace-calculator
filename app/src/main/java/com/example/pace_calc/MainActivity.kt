package com.example.pace_calc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pace_calc.fragments.SettingsFragment
import com.example.pace_calc.fragments.PaceFragment
import com.example.pace_calc.fragments.TimeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val timeFragment = TimeFragment();
        val paceFragment = PaceFragment();
        val settingsFragment = SettingsFragment();
        makeCurrentFragment(timeFragment);



        val bottomNavigiation = findViewById<BottomNavigationView>(R.id.bottom_navigation);
        bottomNavigiation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.timeId -> makeCurrentFragment(timeFragment)
                    R.id.paceId -> makeCurrentFragment(paceFragment)
                    R.id.helpId -> makeCurrentFragment(settingsFragment)
                }
                true;
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit();
        }
    }
}

