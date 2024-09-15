package de.weemeal.backend.domain.service

import de.weemeal.backend.domain.model.Ingredient
import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.port.`in`.RecipePort
import de.weemeal.backend.domain.port.out.RecipeRepositoryPort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RecipeService(
    private val recipeRepositoryPort: RecipeRepositoryPort,
) : RecipePort {

    override fun saveRecipe(recipe: Recipe): Recipe {
        return recipeRepositoryPort.save(recipe)
    }

    override fun getRecipe(recipeId: UUID): Recipe? {
        return recipeRepositoryPort.findRecipe(recipeId)
    }

    override fun getAllRecipes(): List<Recipe>? {
        return recipeRepositoryPort.findAllRecipes()
    }

    override fun deleteRecipe(recipeId: UUID): Boolean {
        recipeRepositoryPort.deleteRecipe(recipeId)
        return recipeRepositoryPort.findRecipe(recipeId) == null
    }

    override fun updateRecipe(updatedRecipe: Recipe): Recipe {
        println("START updateRecipe")
        println(updatedRecipe.recipeId)
        val recipeFromDatabase = updatedRecipe.recipeId?.let {
            println("START recipeRepositoryPort.findRecipe(it) ")
            recipeRepositoryPort.findRecipe(it)
        }
            ?: throw IllegalArgumentException("Rezept nicht gefunden")

        println(recipeFromDatabase)
        val ingredientsToRemove = recipeFromDatabase.ingredients.getRemovedIngredients(updatedRecipe.ingredients)
        ingredientsToRemove.forEach { recipeRepositoryPort.removeIngredient(it) }

        return recipeRepositoryPort.save(updatedRecipe)
    }

    private fun List<Ingredient>.getRemovedIngredients(other: List<Ingredient>): List<Ingredient> {
        return this.filter { ingredient ->
            other.none { ingredient.ingredientName == it.ingredientName }
        }
    }
}