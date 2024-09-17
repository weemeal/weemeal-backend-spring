package de.weemeal.backend.domain.port.out

import de.weemeal.backend.domain.model.Recipe

interface RecipeImageGeneratorPort {
    fun generateRecipeImage(recipe: Recipe): String?
}