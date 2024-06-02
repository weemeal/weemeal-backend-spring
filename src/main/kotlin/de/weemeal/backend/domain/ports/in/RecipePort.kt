package de.weemeal.backend.domain.ports.`in`

import de.weemeal.backend.domain.model.Recipe

interface RecipePort {
    fun getAllRecipes(): List<Recipe>
    fun createRecipe(recipe: Recipe): Recipe
}