package com.example.lab02asssignment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab02asssignment.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
  private lateinit var binding: ActivitySearchBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivitySearchBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.goBack.setOnClickListener { gotoProfileActivity() }
  }

  private fun gotoProfileActivity() {
    val intent = Intent(this, ProfileActivity::class.java)
    startActivity(intent)
  }
}