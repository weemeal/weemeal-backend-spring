package de.weemeal.backend.domain.port.out

import de.weemeal.backend.domain.model.Ingredient
import de.weemeal.backend.domain.model.Recipe
import java.util.UUID

interface RecipeRepositoryPort {
    fun save(recipe: Recipe): Recipe
    fun findAllRecipes(): List<Recipe>?
    fun findRecipe(recipeId: UUID): Recipe?
    fun deleteRecipe(recipeId: UUID)
    fun removeIngredient(ingredient: Ingredient)
}