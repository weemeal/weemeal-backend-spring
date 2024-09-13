package de.weemeal.backend.adapter.out

import de.weemeal.backend.adapter.out.persistence.IngredientRepository
import de.weemeal.backend.adapter.out.persistence.entity.IngredientEntity
import de.weemeal.backend.domain.model.Ingredient
import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.port.out.IngredientRepositoryPort
import org.springframework.stereotype.Component
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@Component
class IngredientPostgresAdapter(private val ingredientRepository: IngredientRepository) : IngredientRepositoryPort {

    override fun save(ingredient: Ingredient, recipe: Recipe): Ingredient {
        return ingredientRepository.save(ingredient.toEntity(recipe)).toDomain()
    }

    override fun findIngredientById(ingredientId: UUID): Ingredient? {
        return ingredientRepository.findById(ingredientId).getOrNull()?.toDomain()
    }

    override fun findIngredientsByRecipeId(recipe: Recipe): List<Ingredient> {
        return ingredientRepository.findByRecipe(recipe.toEntity())?.toList()!!.toIngredientListDomain()
    }

    override fun deleteIngredient(ingredientId: UUID) {
        return ingredientRepository.deleteById(ingredientId)
    }
}

fun IngredientEntity.toDomain(): Ingredient {
    return Ingredient(
        ingredientId = this.ingredientId,
        ingredientName = this.ingredientName,
        amount = this.amount,
        unit = this.unit,
    )
}

fun List<IngredientEntity>.toIngredientListDomain(): List<Ingredient> {
    val ingredientList = mutableListOf<Ingredient>()
    this.forEach { ingredientEntity ->
        ingredientList.add(ingredientEntity.toDomain())
    }
    return ingredientList
}

fun Ingredient.toEntity(recipe: Recipe): IngredientEntity {
    return IngredientEntity(
        ingredientId = this.ingredientId,
        ingredientName = this.ingredientName,
        amount = this.amount,
        unit = this.unit,
        recipe = recipe.toEntity()
    )
}