package de.weemeal.backend.domain.model.ingredient

import java.util.UUID

data class Ingredient(
    override var id: UUID? = UUID.randomUUID(),
    override var position: Int = 0,
    override var contentType: ContentType = ContentType.INGREDIENT,
    var ingredientName: String? = null,
    var unit: String? = null,
    var amount: Float? = null
) : IngredientListContent(id, position, contentType)