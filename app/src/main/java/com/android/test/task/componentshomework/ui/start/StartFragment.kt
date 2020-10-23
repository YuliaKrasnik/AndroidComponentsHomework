package com.android.test.task.componentshomework.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import com.android.test.task.componentshomework.R
import com.android.test.task.componentshomework.base.BaseFragment

class StartFragment : BaseFragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val fragmentLayout = inflater.inflate(R.layout.fragment_start, container, false)

        val navController = NavHostFragment.findNavController(this)

        val buttonNavigation = fragmentLayout.findViewById<Button>(R.id.btn_navigation)
        buttonNavigation.setOnClickListener { navController.navigate(R.id.fragmentA) }

        val buttonService = fragmentLayout.findViewById<Button>(R.id.btn_service)
        buttonService.setOnClickListener { navController.navigate(R.id.serviceFragment) }

        val buttonContentProvider = fragmentLayout.findViewById<Button>(R.id.btn_content_provider)
        buttonContentProvider.setOnClickListener { navController.navigate(R.id.listFragment) }

        return fragmentLayout
    }

}