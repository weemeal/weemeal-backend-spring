package de.weemeal.backend.adapter.out

import de.weemeal.backend.adapter.mapper.RecipeMapper.toEntity
import de.weemeal.backend.adapter.out.persistence.RecipeRepository
import de.weemeal.backend.adapter.out.persistence.entity.RecipeEntity.Companion.toDomain
import de.weemeal.backend.adapter.out.persistence.entity.RecipeEntity.Companion.toRecipeListDomain
import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.port.out.RecipeRepositoryPort
import org.springframework.stereotype.Component
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@Component
class RecipePostgresAdapter(
    private val recipeRepository: RecipeRepository
) : RecipeRepositoryPort {

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
        recipeRepository.deleteById(recipeId)
    }
}









