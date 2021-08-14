package com.example.pace_calc.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.pace_calc.Calculator
import com.example.pace_calc.Pace
import com.example.pace_calc.R
import com.example.pace_calc.Time

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PaceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaceFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calculator = Calculator()
        var time = Time(0, 25, 30);

        val enterDistance: EditText = view.findViewById(R.id.editDistanceForPace);
        enterDistance.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                try {
                    v.text.toString().toDouble();
                } catch (e: Exception) {
                    //Toast.makeText(this@MainActivity, "Distance must be a number!", Toast.LENGTH_SHORT).show()
                }
                return@OnEditorActionListener true
            }
            false
        })

        val editTime: EditText = view.findViewById(R.id.editTimeForPace);

        editTime.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setupPaceEntered();
            }

            fun setupPaceEntered() {
                val text = editTime.text.toString();
                val hoursMinutesSeconds = text.split(":");
                try {
                    if (hoursMinutesSeconds.count() == 3) {
                        // has hours too


                    } else {
                        val minutes = Integer.parseInt(hoursMinutesSeconds[0]);
                        val seconds = Integer.parseInt(hoursMinutesSeconds[1]);
                        time = Time(0, minutes, seconds);
                    }


                } catch (e: Exception) {
                    //Toast.makeText(this, "Must be a valid time in format hh:mm:ss", Toast.LENGTH_SHORT).show()
                }
            }
        })

        val calculateButton: Button = view.findViewById(R.id.calculatePaceButton);

        calculateButton.setOnClickListener {
            val resultField: TextView = view.findViewById(R.id.paceResultTextView);
            val distanceEntered = enterDistance.text.toString().toDouble();
            val calculatedPace = calculator.calculatePace(distanceEntered, time);
            resultField.text = calculatedPace;
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pace, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PaceFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PaceFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}