package com.example.lab02asssignment.api.model

data class Profile(
  val id: Int,
  val firstName: String,
  val lastName: String,
  val profileImage: String,
  val coverImage: String
) {
  fun fullName(): String {
    return "$firstName $lastName"
  }
}
