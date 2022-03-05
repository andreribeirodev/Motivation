package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences

class MainActivity : AppCompatActivity() {

    private lateinit var textViewName: TextView
    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewName = findViewById(R.id.textViewName)

        mSecurityPreferences = SecurityPreferences(this)
        textViewName.setText(mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME))

    }
}