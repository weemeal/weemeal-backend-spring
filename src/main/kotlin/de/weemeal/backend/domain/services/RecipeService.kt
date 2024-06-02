package de.weemeal.backend.domain.services

import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.ports.`in`.RecipePort
import de.weemeal.backend.domain.ports.out.RecipeRepositoryPort

class RecipeService(private val recipeRepositoryPort: RecipeRepositoryPort) : RecipePort {

    override fun getAllRecipes(): List<Recipe> {
        return recipeRepositoryPort.findAllRecipes()
    }

    override fun createRecipe(recipe: Recipe): Recipe {
        return recipeRepositoryPort.save(recipe)
    }
}