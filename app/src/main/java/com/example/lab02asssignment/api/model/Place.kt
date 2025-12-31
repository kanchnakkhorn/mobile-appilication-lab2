package com.example.lab02asssignment.api.model

import java.io.Serializable

data class Place(
  val id: Int,
  val name: String,
  val imageUrl: String
) : Serializable
