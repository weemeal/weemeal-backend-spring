package de.weemeal.backend.domain.port.outbound

import de.weemeal.backend.domain.model.Recipe
import java.util.UUID

interface ForLoadingRecipe {
    fun findAllRecipes(): List<Recipe>?
    fun findRecipe(recipeId: UUID): Recipe?
}