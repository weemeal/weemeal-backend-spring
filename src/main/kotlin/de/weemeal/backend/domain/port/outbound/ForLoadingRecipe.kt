package de.weemeal.backend.domain.port.outbound

import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.model.RecipeId

interface ForLoadingRecipe {
    fun findAllRecipes(): List<Recipe>?
    fun findRecipe(recipeId: RecipeId): Recipe?
}