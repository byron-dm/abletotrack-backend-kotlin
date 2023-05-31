package com.lwsoftware.abletotrack.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Lob
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "users_profiles")
class UserProfile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ID")
  var id: Long = 0

  @OneToOne
  @MapsId
  @JoinColumn(name = "USER_ID")
  lateinit var user: User

  @Column(name = "FIRST_NAME", nullable = false)
  var firstName = ""

  @Column(name = "LAST_NAME", nullable = false)
  var lastName = ""

  @Column(name = "PICTURE")
  @Lob
  var picture: ByteArray = ByteArray(0)
}