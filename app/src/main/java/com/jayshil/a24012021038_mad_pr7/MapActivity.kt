package com.jayshil.a24012021038_mad_pr7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jayshil.a24012021038_mad_pr7.databinding.ActivityMapBinding

class MapActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val person = intent.getSerializableExtra("Object") as Person

        binding.txtName.text = person.name
        binding.txtLatitude.text = "Latitude: ${person.latitude}"
        binding.txtLongitude.text = "Longitude: ${person.longitude}"
        binding.txtAddress.text = person.address
    }
}