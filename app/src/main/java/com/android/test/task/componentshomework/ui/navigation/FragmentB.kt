package com.android.test.task.componentshomework.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import com.android.test.task.componentshomework.R
import com.android.test.task.componentshomework.base.BaseFragment

class FragmentB : BaseFragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val fragmentLayout = inflater.inflate(R.layout.fragment_b, container, false)

        val navController = NavHostFragment.findNavController(this)

        val button = fragmentLayout.findViewById<Button>(R.id.btn_on_fragment_C)
        button.setOnClickListener { navController.navigate(R.id.action_fragmentB_to_fragmentC) }

        return fragmentLayout
    }

}