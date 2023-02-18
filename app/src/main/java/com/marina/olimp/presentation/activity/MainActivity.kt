package com.marina.olimp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marina.olimp.R
import com.marina.olimp.presentation.detail.fragment.ServiceDetailFragment
import com.marina.olimp.presentation.entity.ServiceUI
import com.marina.olimp.presentation.list.fragment.ServiceListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, ServiceListFragment.newInstance())
            .commit()
    }

    fun goToDetail(service: ServiceUI) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container_view, ServiceDetailFragment.newInstance(service))
            .commit()
    }
}