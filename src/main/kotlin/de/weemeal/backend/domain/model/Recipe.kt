package de.weemeal.backend.domain.model

import java.util.UUID

data class Recipe(
    var recipeId: UUID? = UUID.randomUUID(),
    var name: String? = null,
    var recipeYield: Int? = null,
    var recipeInstructions: String? = null,
    var ingredients: List<Ingredient> = emptyList(),
)