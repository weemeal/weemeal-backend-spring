package de.weemeal.backend.adapter.out.persistence.entity

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface IngredientRepository : CrudRepository<IngredientEntity, UUID>