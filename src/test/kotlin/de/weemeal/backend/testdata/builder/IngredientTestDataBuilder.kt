package de.weemeal.backend.testdata.builder

import de.weemeal.backend.domain.model.Ingredient
import java.util.UUID

class IngredientTestDataBuilder {
    private var ingredientId: UUID? = null
    private var ingredientName: String? = null
    private var amount: Float? = null
    private var unit: String? = null
    private var position: Int? = null

    fun ingredientId(ingredientId: UUID) = apply { this.ingredientId = ingredientId }
    fun ingredientName(ingredientName: String) = apply { this.ingredientName = ingredientName }
    fun amount(amount: Float) = apply { this.amount = amount }
    fun unit(unit: String) = apply { this.unit = unit }
    fun position(position: Int) = apply { this.position = position }

    fun build(): Ingredient = Ingredient(
        ingredientId = ingredientId,
        ingredientName = ingredientName,
        amount = amount,
        unit = unit,
        position = position!!
    )
}