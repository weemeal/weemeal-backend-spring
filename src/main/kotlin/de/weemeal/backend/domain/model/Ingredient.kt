package de.weemeal.backend.domain.model

import java.util.UUID

data class Ingredient(
    var ingredientId: UUID? = UUID.randomUUID(),
    var ingredientName: String? = null,
    var unit: String? = null,
    var amount: String? = null,
)