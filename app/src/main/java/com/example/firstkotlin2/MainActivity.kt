package com.example.firstkotlin2

import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Timer
import java.util.TimerTask


class MainActivity : AppCompatActivity() {

    private lateinit var minutesText : TextView
    private lateinit var secondsText : TextView
    private lateinit var millisecondsText : TextView
    private lateinit var buttonStart : Button
    private lateinit var buttonStop : Button
    private lateinit var buttonReset : Button
    private lateinit var timer: Timer
    private var startTime = 0L
    private var timeWhenStopped = 0L
    var isRunning = false





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        minutesText = findViewById(R.id.minutes)
        secondsText = findViewById(R.id.seconds)
        millisecondsText = findViewById(R.id.milliseconds)
        buttonStart = findViewById(R.id.startButton)
        buttonStop = findViewById(R.id.stopButton)
        buttonReset = findViewById(R.id.resetButton)
        timer = Timer()
        var elapsedMillis:Long

        fun startTimer() {
            timer.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    if (isRunning) {
                        elapsedMillis = SystemClock.elapsedRealtime() - startTime
                        val millis = ((elapsedMillis % 1000) / 10).toInt()
                        val seconds = ((elapsedMillis % 60000) /1000).toInt()
                        val minutes = (elapsedMillis / 60000000).toInt()
                        runOnUiThread {
                            millisecondsText.text = String.format("%02d", millis)
                            secondsText.text = String.format("%02d", seconds)
                            minutesText.text = String.format("%02d", minutes)
                        }
                    }
                    }
            }, 0, 10)

        }


        buttonStart.setOnClickListener {
            if (!isRunning) {
                startTime = SystemClock.elapsedRealtime() - timeWhenStopped // Запоминаем время остановки
                isRunning = true
                startTimer()
            }


        }
        buttonStop.setOnClickListener {
            isRunning = false
            timeWhenStopped = SystemClock.elapsedRealtime() - startTime
        }
        buttonReset.setOnClickListener {
            isRunning = false
            timeWhenStopped = 0L
            millisecondsText.text = String.format("%02d", 0)
            secondsText.text = String.format("%02d", 0)
            minutesText.text = String.format("%02d", 0)
        }


    }
}





























//buttonStart.setOnClickListener {
////            startWatch()
////        }
////
////        buttonStop.setOnClickListener {
////            isRunning = false
////        }
////
////        buttonReset.setOnClickListener {
////            if (!isRunning) {
////                milliseconds = 0
////                seconds = 0
////                minutes = 0
////                updateUI(milliseconds, seconds, minutes)
////            } else {
////                Toast.makeText(this, "ОСТАНОВИТЕ ТАЙМЕР", Toast.LENGTH_SHORT).show()
////            }
//}
//private fun startWatch() {

//    isRunning = true
//    CoroutineScope(Dispatchers.Main).launch {
//        while (isRunning) {
//            millis++
//            delay(1)
//            if (millis == 10) {
//                millis = 0
//                milliseconds++
//                updateUI(milliseconds, seconds, minutes)
//            }
//
//            if (milliseconds == 100) {
//                milliseconds = 0
//                seconds++
//            }
//            if (seconds == 60) {
//                seconds = 0
//                minutes++
//            }
//
//
//        }
//
//    }
//
//}