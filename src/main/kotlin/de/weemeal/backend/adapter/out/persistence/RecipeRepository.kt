package de.weemeal.backend.adapter.out.persistence

import de.weemeal.backend.adapter.out.persistence.entities.RecipeEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RecipeRepository : CrudRepository<RecipeEntity, String>