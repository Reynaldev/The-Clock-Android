package com.reyndev.theclock

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.reyndev.theclock.databinding.ActivityMainBinding
import kotlinx.coroutines.Runnable
import java.lang.StringBuilder
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var calendar: GregorianCalendar

    private var hour: String = "00"
    private var minute: String = "00"
    private var second: String = "00"

    private var isResumed: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calendar = GregorianCalendar()

        Handler().post(object : Runnable {
            override fun run() {
                calendar.time = Date()

                hour = timeToString(calendar.get(Calendar.HOUR_OF_DAY))
                minute = timeToString(calendar.get(Calendar.MINUTE))
                second = timeToString(calendar.get(Calendar.SECOND))

                binding.clockText.text = "${hour}:${minute}:${second}"

                Handler().postDelayed(this, 500)
            }
        })
    }

    fun timeToString(time: Int): String {
        var timeStr = StringBuilder(time.toString())

        if (timeStr.length < 2)
            return timeStr.insert(0, '0').toString()
        else
            return timeStr.toString()
    }
}