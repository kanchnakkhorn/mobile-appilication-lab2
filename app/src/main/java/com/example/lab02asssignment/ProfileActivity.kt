package com.example.lab02asssignment

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.lab02asssignment.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
  private val activityLauncher = registerForActivityResult(
    ActivityResultContracts.StartActivityForResult()
  ) {
    result ->
    if (result.resultCode == RESULT_OK) {
      val newName = result.data?.getStringExtra("new-name")
      binding.profileName.text = newName
      binding.profileNameTop.text = newName
    }
  }
  private val name = "Sok Dara";
  private lateinit var binding: ActivityProfileBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.enableEdgeToEdge(window)

  // Set up UI
  // setContentView(R.layout.activity_profile) This is an old method
    binding = ActivityProfileBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // Setup event listener
    binding.addStory.setOnClickListener { openSearchActivity() }
    binding.editProfileBig.setOnClickListener { openEditProfileActivity() }

    loadAndDisplayProfile()
  }

  private fun openSearchActivity() {
    val intent = Intent(this, SearchActivity::class.java)
    startActivity(intent)
  }

  private fun openEditProfileActivity() {
    val intent = Intent(this, EditProfileActivity::class.java)
    intent.putExtra("name", name)
//    startActivity(intent)
    activityLauncher.launch(intent)
  }

  private fun loadAndDisplayProfile() {
    binding.profileName.text = name
    binding.profileNameTop.text = name
  }

}