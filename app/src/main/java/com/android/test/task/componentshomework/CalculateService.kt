package com.android.test.task.componentshomework

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.Runnable
import java.lang.Thread as Thread

class CalculateService : Service() {
    companion object {
        const val KEY_INTENT_CALCULATE = "calculateResult"
        const val IDENTIFY_INTENT_CALCULATE = "serviceCalculate"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        calculateSomeValue()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun calculateSomeValue() {
        Thread(Runnable {
            var result = 0
            val randomNumber = (0..1000).random()

            for (value in 0..randomNumber) {
                result += value
            }
            Thread.sleep(3000)

            val intent = Intent(IDENTIFY_INTENT_CALCULATE)
            intent.putExtra(KEY_INTENT_CALCULATE, result)
            sendBroadcast(intent)
        }).start()
        stopSelf()
    }

    override fun onBind(p0: Intent?): IBinder? = null
}
