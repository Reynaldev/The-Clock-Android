package com.reyndev.theclock

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.reyndev.theclock.databinding.ActivityMainBinding
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var calendar: GregorianCalendar

    private var hour: Int = 0
    private var minute: Int = 0
    private var second: Int = 0

    private var isResumed: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calendar = GregorianCalendar()

        Handler().post(object : Runnable {
            override fun run() {
                calendar.time = Date()

                hour = calendar.get(Calendar.HOUR_OF_DAY)
                minute = calendar.get(Calendar.MINUTE)
                second = calendar.get(Calendar.SECOND)

                binding.clockText.text = "${hour}:${minute}:${second}"

                Handler().postDelayed(this, 500)
            }
        })
    }
}