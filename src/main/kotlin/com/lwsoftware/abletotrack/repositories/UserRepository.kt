package com.lwsoftware.abletotrack.repositories

import com.lwsoftware.abletotrack.entities.User
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

  private fun updateUser(userId:Long, criteriaBuilder: CriteriaBuilder, shouldRememberMe: Boolean) {
    val criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User::class.java)
    val user = criteriaUpdate.from(User::class.java)
    criteriaUpdate.where(criteriaBuilder.equal(user.get<Long>("id"), userId))
    criteriaUpdate.set("shouldRememberMe", shouldRememberMe.toInt())

    entityManager.createQuery(criteriaUpdate).executeUpdate()
  }
}