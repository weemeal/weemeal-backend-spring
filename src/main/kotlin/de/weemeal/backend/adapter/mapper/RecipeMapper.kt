package de.weemeal.backend.adapter.mapper

import de.weemeal.backend.adapter.mapper.IngredientMapper.toEntity
import de.weemeal.backend.adapter.out.persistence.entity.RecipeEntity
import de.weemeal.backend.domain.model.Recipe

object RecipeMapper {
    fun Recipe.toEntity(): RecipeEntity {
        return RecipeEntity(
            recipeId = this.recipeId,
            name = this.name,
            recipeYield = this.recipeYield,
            recipeInstructions = this.recipeInstructions,
            ingredients = this.ingredients?.map { it.toEntity() }?.toList()
        )
    }
}
