package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.motivation.R
import com.example.motivation.databinding.ActivityMainBinding
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.repository.Mock

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mSecurityPreferences: SecurityPreferences
    private var mPhraseFilter: Int = MotivationConstants.PHRASEFILTER.ALL


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(supportActionBar != null){
            supportActionBar!!.hide()
        }

        mSecurityPreferences = SecurityPreferences(this)
        binding.textViewName.setText(mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME))


        binding.imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
        handleNewPhrase()


        binding.buttomNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageMorning.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)


    }


    override fun onClick(view: View) {
        val id = view.id
        val listFilter = listOf(R.id.imageAll, R.id.imageHappy, R.id.imageMorning)

        if (id == R.id.buttomNewPhrase) {
            handleNewPhrase()
        } else if (id in listFilter) {
            handleFilter(id)
        }
    }

    private fun handleFilter(id: Int) {

        binding.imageAll.setColorFilter(resources.getColor(R.color.white))
        binding.imageHappy.setColorFilter(resources.getColor(R.color.white))
        binding.imageMorning.setColorFilter(resources.getColor(R.color.white))


        when (id) {

            R.id.imageAll -> {
                binding.imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.imageHappy -> {
                binding.imageHappy.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.imageMorning -> {
                binding.imageMorning.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.MORNING
            }
        }

    }

    private fun handleNewPhrase() {
        binding.textPhrase.text = Mock().getPhrase(mPhraseFilter)
    }
}