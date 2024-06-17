package de.weemeal.backend.adapter.out.persistence

import de.weemeal.backend.adapter.out.persistence.entity.RecipeEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RecipeRepository : CrudRepository<RecipeEntity, UUID>