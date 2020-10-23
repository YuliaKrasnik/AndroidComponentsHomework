package com.android.test.task.componentshomework.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.test.task.componentshomework.R
import com.android.test.task.componentshomework.base.BaseFragment

class FragmentC : BaseFragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_c, container, false)
    }

}