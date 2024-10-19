package de.weemeal.backend.adapter.out

import de.weemeal.backend.adapter.mapper.RecipeMapper.toEntity
import de.weemeal.backend.adapter.out.persistence.RecipeRepository
import de.weemeal.backend.adapter.out.persistence.entity.RecipeEntity.Companion.toDomain
import de.weemeal.backend.adapter.out.persistence.entity.RecipeEntity.Companion.toRecipeListDomain
import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.model.RecipeId
import de.weemeal.backend.domain.port.outbound.ForDeletingRecipe
import de.weemeal.backend.domain.port.outbound.ForLoadingRecipe
import de.weemeal.backend.domain.port.outbound.ForSavingRecipe
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class RecipePostgresAdapter(
    private val recipeRepository: RecipeRepository
) : ForDeletingRecipe, ForSavingRecipe, ForLoadingRecipe {

    override fun save(recipe: Recipe): Recipe {
        return recipeRepository.save(recipe.toEntity()).toDomain()
    }

    override fun findRecipe(recipeId: RecipeId): Recipe? {
        return recipeRepository.findById(recipeId.value).getOrNull()?.toDomain()
    }

    override fun findAllRecipes(): List<Recipe>? {
        return recipeRepository.findAll().toList().toRecipeListDomain()
    }

    override fun deleteRecipe(recipeId: RecipeId) {
        recipeRepository.deleteById(recipeId.value)
    }
}