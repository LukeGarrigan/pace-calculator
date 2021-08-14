package com.example.pace_calc

class Pace(val minutes: Int, val seconds: Int) {

    init {
        if (minutes >= 60 || seconds >= 60) {
            throw Exception();
        }
    }

}