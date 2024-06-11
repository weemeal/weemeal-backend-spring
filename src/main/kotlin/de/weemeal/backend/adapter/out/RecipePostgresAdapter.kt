package de.weemeal.backend.adapter.out

import de.weemeal.backend.adapter.out.persistence.RecipeRepository
import de.weemeal.backend.adapter.out.persistence.entity.RecipeEntity
import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.port.out.RecipeRepositoryPort
import org.springframework.stereotype.Component

@Component
class RecipePostgresAdapter(private val recipeRepository: RecipeRepository) : RecipeRepositoryPort {

    override fun findAllRecipes(): List<Recipe> {
        return recipeRepository.findAll().toList().toRecipeListDomain()
    }

    override fun save(recipe: Recipe): Recipe {
        return recipeRepository.save(recipe.toEntity()).toDomain()
    }
}

fun RecipeEntity.toDomain(): Recipe {
    return Recipe(
        recipeId = this.recipeId,
        name = this.name,
        recipeYield = this.recipeYield,
        recipeInstructions = this.recipeInstructions,
//        ingredients = this.ingredients,
    )
}

fun List<RecipeEntity>.toRecipeListDomain(): List<Recipe> {
    val recipeList = mutableListOf<Recipe>()
    this.forEach { recipeEntity ->
        recipeList.add(recipeEntity.toDomain())
    }
    return recipeList
}

fun Recipe.toEntity(): RecipeEntity {
    return RecipeEntity(
        recipeId = this.recipeId,
        name = this.name,
        recipeYield = this.recipeYield,
        recipeInstructions = this.recipeInstructions,
//        ingredients = this.ingredients,
    )
}