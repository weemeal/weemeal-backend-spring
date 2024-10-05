package de.weemeal.backend.adapter.mapper

import de.weemeal.backend.adapter.out.persistence.entity.IngredientEntity
import de.weemeal.backend.domain.model.Ingredient

object IngredientMapper {
    fun Ingredient.toEntity(): IngredientEntity {
        return IngredientEntity(
            ingredientId = this.ingredientId,
            ingredientName = this.ingredientName,
            amount = this.amount,
            unit = this.unit,
            position = this.position
        )
    }
}
