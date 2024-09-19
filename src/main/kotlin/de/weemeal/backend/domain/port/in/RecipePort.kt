package de.weemeal.backend.domain.port.`in`

import de.weemeal.backend.domain.model.Recipe
import java.util.UUID

interface RecipePort {
    fun saveRecipe(recipe: Recipe): Recipe
    fun getAllRecipes(): List<Recipe>?
    fun getRecipe(recipeId: UUID): Recipe?
    fun deleteRecipe(recipeId: UUID): Boolean
    fun generateBringHtml(recipeId: UUID): String
}