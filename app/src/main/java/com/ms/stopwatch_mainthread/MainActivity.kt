package com.ms.stopwatch_mainthread

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.jetbrains.annotations.NotNull

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val inputTime = findViewById<EditText>(R.id.enterTime)
        val btnStart =  findViewById<Button>(R.id.btnStart)
        val showResult = findViewById<TextView>(R.id.showResult)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)

        // Background Task
        btnStart.setOnClickListener {
            if (inputTime.text.isNotEmpty()) {
                tvTitle.text = "Performing Background Task"
                val thread = Thread(Runnable {
                    val input = (inputTime.text).toString()
                    val result = Integer.parseInt(input)
                    for (i in result downTo 0) {

                        runOnUiThread() {
                            showResult.text = i.toString()
                            Log.d("MainActivity", "$i")
                            if (i == 0) {
                                tvTitle.text = "Background Task is completed!"
                                showResult.text = ""
                            }
                        }
                        Thread.sleep(1000)
                    }
                })
                thread.start()
            } else { showResult.text = "Input is required!" }
        }

        // Performing another task
        val btnTest = findViewById<Button>(R.id.btnTest)
        btnTest.setOnClickListener {
            Toast.makeText(this,"Button is Clicked", Toast.LENGTH_LONG).show()
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:8809197034"))
            startActivity(intent)
        }
    }
}