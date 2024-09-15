package de.weemeal.backend.adapter.out.persistence.entity

import de.weemeal.backend.adapter.out.persistence.entity.IngredientEntity.Companion.toDomain
import de.weemeal.backend.domain.model.Recipe
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.Objects
import java.util.UUID

@Entity
@Table(name = "recipe")
data class RecipeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var recipeId: UUID?,
    var name: String? = null,
    var recipeYield: Int? = null,

    @Column(columnDefinition = "TEXT")
    var recipeInstructions: String? = null,

    @OneToMany(mappedBy = "recipeEntity", cascade = [CascadeType.ALL])
    var ingredients: List<IngredientEntity>? = null,
){
    init {
        ingredients?.forEach { it.recipeEntity = this }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RecipeEntity) return false

        return recipeId == other.recipeId &&
                name == other.name &&
                recipeYield == other.recipeYield &&
                recipeInstructions == other.recipeInstructions
    }

    override fun hashCode(): Int {
        return Objects.hash(recipeId, name, recipeYield, recipeInstructions)
    }


    companion object{
        fun RecipeEntity.toDomain(): Recipe {
            return Recipe(
                recipeId = this.recipeId,
                name = this.name,
                recipeYield = this.recipeYield,
                recipeInstructions = this.recipeInstructions,
                ingredients = this.ingredients?.map { it.toDomain() } ?: emptyList(),
            )
        }

        fun List<RecipeEntity>.toRecipeListDomain(): List<Recipe> {
            val recipeList = mutableListOf<Recipe>()
            this.forEach { recipeEntity ->
                recipeList.add(recipeEntity.toDomain())
            }
            return recipeList
        }
    }
}