package de.weemeal.backend.domain.model

import java.util.UUID

data class Recipe(
    var recipeId: String = UUID.randomUUID().toString(),
    var name: String? = null,
    var recipeYield: Int? = null,
    var recipeInstructions: String? = null,
    var ingredients: List<String>? = null,
)