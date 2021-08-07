package com.example.pace_calc

import java.lang.Math.floor

class Pace(val minutes: Int, val seconds: Int) {

    init {
        if (minutes >= 60 || seconds >= 60) {
            throw Exception();
        }
    }

    fun calculateTime(distanceEntered: Double) : String {
        val totalSeconds = distanceEntered * this.seconds;
        val totalMinutesInSeconds = (distanceEntered * this.minutes)*60;
        val minutesAndSeconds = totalSeconds + totalMinutesInSeconds;

        val minutesToDisplay = floor(minutesAndSeconds / 60);
        val secondsToDisplay = minutesAndSeconds % 60;

        return "${minutesToDisplay.toInt()} mins ${secondsToDisplay.toInt()} seconds";
    }
}