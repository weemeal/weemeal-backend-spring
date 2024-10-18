package de.weemeal.backend.domain.service

import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.model.ingredient.Ingredient
import de.weemeal.backend.domain.port.`in`.RecipePort
import de.weemeal.backend.domain.port.out.RecipeRepositoryPort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
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

    override fun generateBringHtml(recipeId: UUID): String {

        val recipe = recipeRepositoryPort.findRecipe(recipeId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

        val ingredientList: List<Ingredient> = recipe.ingredientListContent
            .filterIsInstance<Ingredient>()

        val ingredientsHtml = ingredientList.joinToString(separator = "\n") {
            "<div itemprop=\"ingredients\">${it.amount ?: ""} ${it.unit} ${it.ingredientName}</div>"
        }

        return """
            <div itemtype="http://schema.org/Recipe">
            <div itemprop="name">${recipe.name}</div>
            <div itemprop="yield">${recipe.recipeYield}</div>
            <div itemprop="recipeInstructions">${recipe.recipeInstructions}</div>
            $ingredientsHtml
            </div>
            """

    }
}