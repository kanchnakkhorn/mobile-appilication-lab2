package com.example.lab02asssignment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab02asssignment.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup UI
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get data from
        val name = intent.getStringExtra("name")
        binding.editName.setText(name)

        binding.editNameSave.setOnClickListener {
            saveProfile()
        }
    }

    private fun saveProfile() {
        val intent = Intent()
        val name = binding.editName.text.toString()
        intent.putExtra("new-name", name)
        setResult(RESULT_OK, intent)
        finish()
    }
}