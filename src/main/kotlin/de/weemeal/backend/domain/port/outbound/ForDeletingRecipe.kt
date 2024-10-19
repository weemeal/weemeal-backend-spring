package de.weemeal.backend.domain.port.outbound

import de.weemeal.backend.domain.model.RecipeId

interface ForDeletingRecipe {
    fun deleteRecipe(recipeId: RecipeId)
}