package de.weemeal.backend.adapter.out.persistence.entity

import de.weemeal.backend.domain.model.ingredient.ContentType
import de.weemeal.backend.domain.model.ingredient.Ingredient
import de.weemeal.backend.domain.model.ingredient.IngredientListContent
import de.weemeal.backend.domain.model.ingredient.SectionCaption
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.Objects
import java.util.UUID

@Entity
@Table(name = "ingredient_list_content")
data class IngredientListContentEntity(
    @Id
    val contentId: UUID?,
    var ingredientName: String? = null,
    var unit: String? = null,
    var amount: Float? = null,
    var sectionName: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var contentType: ContentType, // "ingredient" oder "section_caption"

    @Column(nullable = false)
    var position: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    var recipeEntity: RecipeEntity? = null,

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is IngredientListContentEntity) return false

        return contentId == other.contentId &&
                ingredientName == other.ingredientName &&
                amount == other.amount &&
                unit == other.unit &&
                sectionName == other.sectionName &&
                contentType == other.contentType &&
                position == other.position
    }

    override fun hashCode(): Int {
        return Objects.hash(contentId, ingredientName, amount, unit, sectionName, contentType, position)
    }

    companion object {

        fun IngredientListContentEntity.toDomain(): IngredientListContent {
            return when (this.contentType) {
                ContentType.INGREDIENT -> {
                    Ingredient(
                        id = this.contentId,
                        ingredientName = this.ingredientName
                            ?: throw IllegalArgumentException("Ingredient name cannot be null"),
                        amount = this.amount,
                        unit = this.unit,
                        position = this.position
                    )
                }

                ContentType.SECTION_CAPTION -> {
                    SectionCaption(
                        id = this.contentId,
                        sectionName = this.sectionName ?: throw IllegalArgumentException("Section name cannot be null"),
                        position = this.position
                    )
                }
            }
        }
    }
}