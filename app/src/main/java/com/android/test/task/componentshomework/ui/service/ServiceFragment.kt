package com.android.test.task.componentshomework.ui.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.android.test.task.componentshomework.CalculateService
import com.android.test.task.componentshomework.R
import com.android.test.task.componentshomework.base.BaseFragment


class ServiceFragment : BaseFragment() {
    lateinit var textViewResult: TextView
    lateinit var receiver: BroadcastReceiver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentLayout = inflater.inflate(R.layout.fragment_service, container, false)

        textViewResult = fragmentLayout.findViewById(R.id.tv_result)

        val intent = Intent(context, CalculateService::class.java)
        val button = fragmentLayout.findViewById<Button>(R.id.btn_start_service)
        button.setOnClickListener { context?.startService(intent) }

        registerBroadcastReceiver()

        return fragmentLayout
    }

    private fun registerBroadcastReceiver() {
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val result = intent?.extras?.get(CalculateService.KEY_INTENT_CALCULATE)
                updateResult(result.toString())
            }
        }

        context?.registerReceiver(
            receiver,
            IntentFilter(CalculateService.IDENTIFY_INTENT_CALCULATE)
        )
    }

    fun updateResult(value: String) {
        textViewResult.text = "Result: $value"
    }

    override fun onPause() {
        super.onPause()
        context?.unregisterReceiver(receiver)
    }

}