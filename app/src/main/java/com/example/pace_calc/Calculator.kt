package com.example.pace_calc

class Calculator {

    fun calculateTime(pace: Pace, distanceEntered: Double) : String {
        val totalSeconds = distanceEntered * pace.seconds;
        val totalMinutesInSeconds = (distanceEntered * pace.minutes) * 60;
        val minutesAndSeconds = totalSeconds + totalMinutesInSeconds;

        val minutesToDisplay = Math.floor(minutesAndSeconds / 60);
        val secondsToDisplay = minutesAndSeconds % 60;

        return "${minutesToDisplay.toInt()} mins ${secondsToDisplay.toInt()} seconds";
    }
}