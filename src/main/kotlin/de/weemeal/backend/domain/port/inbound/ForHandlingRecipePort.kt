package de.weemeal.backend.domain.port.inbound

import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.model.RecipeId

interface ForHandlingRecipePort {
    fun saveRecipe(recipe: Recipe): Recipe
    fun getAllRecipes(): List<Recipe>?
    fun getRecipe(recipeId: RecipeId): Recipe?
    fun deleteRecipe(recipeId: RecipeId): Boolean
}