package de.weemeal.backend.adapter.mapper

import de.weemeal.backend.adapter.out.persistence.entity.IngredientListContentEntity
import de.weemeal.backend.domain.model.ingredient.ContentType
import de.weemeal.backend.domain.model.ingredient.Ingredient
import de.weemeal.backend.domain.model.ingredient.IngredientListContent
import de.weemeal.backend.domain.model.ingredient.SectionCaption

object IngredientListContentMapper {
    fun IngredientListContent.toEntity(): IngredientListContentEntity {
        return when (this) {
            is Ingredient -> IngredientListContentEntity(
                contentId = this.id,
                ingredientName = this.ingredientName,
                amount = this.amount,
                unit = this.unit,
                sectionName = null, // Ingredients haben keine Sektion
                contentType = ContentType.INGREDIENT,
                position = this.position,
            )

            is SectionCaption -> IngredientListContentEntity(
                contentId = this.id,
                ingredientName = null, // Sektionen haben keinen Zutatennamen
                amount = null, // Sektionen haben keine Mengenangaben
                unit = null, // Sektionen haben keine Einheit
                sectionName = this.sectionName,
                contentType = ContentType.SECTION_CAPTION,
                position = this.position,
            )

            else -> {
                throw Exception()
            }
        }
    }
}
