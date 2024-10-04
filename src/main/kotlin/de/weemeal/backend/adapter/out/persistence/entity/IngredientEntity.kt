package de.weemeal.backend.adapter.out.persistence.entity

import de.weemeal.backend.domain.model.Ingredient
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.Objects
import java.util.UUID

@Entity
@Table(name = "ingredient")
data class IngredientEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var ingredientId: UUID?,
    var ingredientName: String? = null,
    var unit: String? = null,
    var amount: Float? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    var recipeEntity: RecipeEntity? = null
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is IngredientEntity) return false

        return ingredientId == other.ingredientId &&
                ingredientName == other.ingredientName &&
                amount == other.amount &&
                unit == other.unit
    }

    override fun hashCode(): Int {
        return Objects.hash(ingredientId, ingredientName, amount, unit)
    }

    companion object{
        fun IngredientEntity.toDomain(): Ingredient {
            return Ingredient(
                ingredientId = this.ingredientId,
                ingredientName = this.ingredientName,
                amount = this.amount,
                unit = this.unit,
            )
        }
    }
}
