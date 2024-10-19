package de.weemeal.backend.domain.model

import de.weemeal.backend.domain.model.ingredient.IngredientListContent
import java.util.UUID

data class Recipe(
    var recipeId: UUID? = UUID.randomUUID(),
    var name: String? = null,
    var recipeYield: Int? = null,
    var recipeInstructions: String? = null,
    var ingredientListContent: List<IngredientListContent> = emptyList(),
)