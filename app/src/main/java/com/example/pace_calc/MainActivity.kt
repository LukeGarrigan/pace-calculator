package com.example.pace_calc

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var pace = Pace(8, 10);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enterDistance: EditText = findViewById(R.id.editDistance);

        enterDistance.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                try {
                    v.text.toString().toDouble();
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, "Distance must be a number!", Toast.LENGTH_SHORT).show()
                }
                return@OnEditorActionListener true
            }
            false
        })

        val editPace: EditText = findViewById(R.id.editPaceTime);

        editPace.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val text = v.text.toString()
                val hoursMinutesSeconds = text.split(":");

                try {
                    val minutes = Integer.parseInt(hoursMinutesSeconds[0]);
                    val seconds = Integer.parseInt(hoursMinutesSeconds[1]);
                    pace = Pace( minutes, seconds);
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, "Must be a valid time in format hh:mm:ss", Toast.LENGTH_SHORT).show()
                }
            }
            false
        }

        val calculateButton: Button = findViewById(R.id.calculate);

        calculateButton.setOnClickListener {
            val resultField: TextView = findViewById(R.id.timeResultTextView);
            val distanceEntered = enterDistance.text.toString().toDouble();
            val timeToRun = pace.calculateTime(distanceEntered);
            resultField.text = timeToRun;
        }
    }
}

