package com.lwsoftware.abletotrack.repositories

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import org.springframework.data.repository.Repository

@Transactional
open class BaseRepository<T, ID> : Repository<T, ID> {

  @PersistenceContext
  protected lateinit var entityManager: EntityManager
}