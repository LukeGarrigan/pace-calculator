package com.example.pace_calc

import java.lang.Math.floor

class Time(val hours: Int, val minutes: Int, val seconds: Int) {

    init {
        if (minutes >= 60 || seconds >= 60) {
            throw Exception();
        }
    }

    fun asSeconds() : Int {
        var total = 0;
        total += seconds;
        total += minutes * 60;
        total += hours * 60 * 60;
        return total;
    }

    fun prettyPrintSeconds(seconds: Double): String {
        val minutes = seconds / 60;
        val secondsLeft = floor(seconds % 60).toInt();
        var secondsOut = secondsLeft.toString();
        if (secondsLeft < 10) {
            secondsOut = "0${secondsOut}";
        }
        return "${floor(minutes).toInt()}:${secondsOut} per mile"
    }

}