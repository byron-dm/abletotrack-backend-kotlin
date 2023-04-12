package com.lwsoftware.abletotrack.repositories

import com.lwsoftware.abletotrack.entities.User
import com.lwsoftware.abletotrack.entities.UserProfile
import com.lwsoftware.abletotrack.extensions.toInt
import jakarta.persistence.criteria.CriteriaBuilder
import org.springframework.stereotype.Repository

@Repository
class UserRepository : BaseRepository<User, Long>() {

  fun getUser(email: String, password: String, shouldRememberMe: Boolean): User? {
    val criteriaBuilder = entityManager.criteriaBuilder
    val criteriaQuery = criteriaBuilder.createQuery(User::class.java)

    val user = criteriaQuery.from(User::class.java)
    val emailPredicate = criteriaBuilder.equal(user.get<String>("email"), email)
    val passwordPredicate = criteriaBuilder.equal(user.get<String>("password"), password)

    criteriaQuery.where(emailPredicate, passwordPredicate)

    val query = entityManager.createQuery(criteriaQuery)

    query.singleResult?.also { updateUser(it.id, criteriaBuilder, shouldRememberMe) }.also { return it }
  }

  fun getUser(userId: Long): User {
    val criteriaBuilder = entityManager.criteriaBuilder
    val criteriaQuery = criteriaBuilder.createQuery(User::class.java)

    val user = criteriaQuery.from(User::class.java)
    val idPredicate = criteriaBuilder.equal(user.get<String>("id"), userId)

    criteriaQuery.where(idPredicate)

    return entityManager.createQuery(criteriaQuery).singleResult
  }

  fun getProfile(userId: Long): UserProfile {
    val criteriaBuilder = entityManager.criteriaBuilder
    val criteriaQuery = criteriaBuilder.createQuery(UserProfile::class.java)

    val profile = criteriaQuery.from(UserProfile::class.java)
    val userPredicate = criteriaBuilder.equal(profile.get<String>("userId"), userId)
    val activePredicate = criteriaBuilder.equal(profile.get<String>("isActive"), 1)

    criteriaQuery.where(userPredicate, activePredicate)

    return entityManager.createQuery(criteriaQuery).singleResult;
  }

  private fun updateUser(userId: Long, criteriaBuilder: CriteriaBuilder, shouldRememberMe: Boolean) {
    val criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User::class.java)
    val user = criteriaUpdate.from(User::class.java)
    criteriaUpdate.where(criteriaBuilder.equal(user.get<Long>("id"), userId))
    criteriaUpdate.set("shouldRememberMe", shouldRememberMe.toInt())

    entityManager.createQuery(criteriaUpdate).executeUpdate()
  }
}