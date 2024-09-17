package de.weemeal.backend.adapter.out.rest

import de.weemeal.backend.application.SpoonacularApi
import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.port.out.RecipeImageGeneratorPort
import org.springframework.stereotype.Component

@Component
class SpoonacularRecipeImageAdapter : RecipeImageGeneratorPort {
    override fun generateRecipeImage(recipe: Recipe): String? {
        val recipes = SpoonacularApi().recipesApi.searchRecipes(query = recipe.name, number = 1)
        return recipes.results.firstOrNull()?.image
    }
}