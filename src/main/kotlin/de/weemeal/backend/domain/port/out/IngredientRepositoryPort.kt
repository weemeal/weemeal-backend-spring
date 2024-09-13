package de.weemeal.backend.domain.port.out

import de.weemeal.backend.domain.model.Ingredient
import de.weemeal.backend.domain.model.Recipe
import java.util.UUID

interface IngredientRepositoryPort {
    fun save(ingredient: Ingredient, recipe: Recipe): Ingredient
    fun findIngredientById(ingredientId: UUID): Ingredient?
    fun findIngredientsByRecipeId(recipe: Recipe): List<Ingredient>
    fun deleteIngredient(ingredientId: UUID)
}