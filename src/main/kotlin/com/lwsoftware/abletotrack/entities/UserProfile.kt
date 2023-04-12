package com.lwsoftware.abletotrack.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users_profiles")
class UserProfile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0

  @Column(name = "user_id", nullable = false)
  val userId: Long = 0;

  @Column(name = "picture")
  lateinit var picture: String

  @Column(name = "is_active", nullable = false)
  val isActive: Int = 1;
}