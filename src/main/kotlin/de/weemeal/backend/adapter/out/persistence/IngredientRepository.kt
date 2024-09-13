package de.weemeal.backend.adapter.out.persistence

import de.weemeal.backend.adapter.out.persistence.entity.IngredientEntity
import de.weemeal.backend.adapter.out.persistence.entity.RecipeEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface IngredientRepository : CrudRepository<IngredientEntity, UUID>{
    fun findByRecipe(recipe: RecipeEntity): List<IngredientEntity>?
}