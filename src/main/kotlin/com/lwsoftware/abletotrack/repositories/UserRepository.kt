package com.lwsoftware.abletotrack.repositories

import com.lwsoftware.abletotrack.entities.User
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

  fun getUser(email: String): User {
    val criteriaBuilder = entityManager.criteriaBuilder
    val criteriaQuery = criteriaBuilder.createQuery(User::class.java)

    val user = criteriaQuery.from(User::class.java)
    val emailPredicate = criteriaBuilder.equal(user.get<String>("email"), email)

    criteriaQuery.where(emailPredicate)

    return entityManager.createQuery(criteriaQuery).singleResult
  }

  fun getAllUsers(): List<User> {
    val criteriaBuilder = entityManager.criteriaBuilder
    val criteriaQuery = criteriaBuilder.createQuery(User::class.java)
    val user = criteriaQuery.from(User::class.java)

    
    return entityManager.createQuery(criteriaQuery.select(user)).resultList
  }

  private fun updateUser(userId: Long, criteriaBuilder: CriteriaBuilder, shouldRememberMe: Boolean) {
    val criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User::class.java)
    val user = criteriaUpdate.from(User::class.java)
    criteriaUpdate.where(criteriaBuilder.equal(user.get<Long>("id"), userId))
    criteriaUpdate.set("shouldRememberMe", shouldRememberMe)

    entityManager.createQuery(criteriaUpdate).executeUpdate()
  }
}