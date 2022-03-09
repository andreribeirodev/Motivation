package com.example.motivation.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonSave: Button
    private lateinit var editTextYourName: EditText

    private lateinit var  mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mSecurityPreferences = SecurityPreferences(this)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        verifyName()

        editTextYourName = findViewById(R.id.editTextYourName)
        buttonSave = findViewById(R.id.buttonSave)
        buttonSave.setOnClickListener(this)



    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonSave) {
            handleSave()
        }
    }

    private fun verifyName(){
        val name = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        if (name != ""){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun handleSave() {
        val name = editTextYourName.text.toString()
        if (name.trim() != "") {
            mSecurityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Digite seu nome", Toast.LENGTH_SHORT).show()
        }
    }
}