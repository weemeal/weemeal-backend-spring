package de.weemeal.backend.adapter.out

import de.weemeal.backend.adapter.out.persistence.RecipeRepository
import de.weemeal.backend.adapter.out.persistence.entity.IngredientEntity
import de.weemeal.backend.adapter.out.persistence.entity.RecipeEntity
import de.weemeal.backend.domain.model.Ingredient
import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.port.out.RecipeRepositoryPort
import org.springframework.stereotype.Component
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@Component
class RecipePostgresAdapter(private val recipeRepository: RecipeRepository) : RecipeRepositoryPort {

    override fun save(recipe: Recipe): Recipe {
        return recipeRepository.save(recipe.toEntity()).toDomain()
    }

    override fun findRecipe(recipeId: UUID): Recipe? {
        return recipeRepository.findById(recipeId).getOrNull()?.toDomain()
    }

    override fun findAllRecipes(): List<Recipe>? {
        return recipeRepository.findAll().toList().toRecipeListDomain()
    }

    override fun deleteRecipe(recipeId: UUID) {
        return recipeRepository.deleteById(recipeId)
    }
}

fun RecipeEntity.toDomain(): Recipe {
    return Recipe(
        recipeId = this.recipeId,
        name = this.name,
        recipeYield = this.recipeYield,
        recipeInstructions = this.recipeInstructions,
        ingredients = this.ingredients?.toIngredientListDomain(),
    )
}

fun List<RecipeEntity>.toRecipeListDomain(): List<Recipe> {
    val recipeList = mutableListOf<Recipe>()
    this.forEach { recipeEntity ->
        recipeList.add(recipeEntity.toDomain())
    }
    return recipeList
}

fun List<Ingredient>.toIngredientListEntity(recipe: Recipe): List<IngredientEntity> {
    val ingredientList = mutableListOf<IngredientEntity>()
    this.forEach { ingredientEntity ->
        ingredientList.add(ingredientEntity.toEntity(recipe))
    }
    return ingredientList
}

fun Recipe.toEntity(): RecipeEntity {

    return RecipeEntity(
        recipeId = this.recipeId,
        name = this.name,
        recipeYield = this.recipeYield,
        recipeInstructions = this.recipeInstructions,
        ingredients = this.ingredients?.toIngredientListEntity(this),
    )
}