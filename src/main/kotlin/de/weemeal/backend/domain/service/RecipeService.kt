package de.weemeal.backend.domain.service

import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.port.`in`.RecipePort
import de.weemeal.backend.domain.port.out.RecipeRepositoryPort
import org.springframework.stereotype.Service

@Service
class RecipeService(private val recipeRepositoryPort: RecipeRepositoryPort) : RecipePort {

    override fun getAllRecipes(): List<Recipe> {
        return recipeRepositoryPort.findAllRecipes()
    }

    override fun createRecipe(recipe: Recipe): Recipe {
        return recipeRepositoryPort.save(recipe)
    }
}