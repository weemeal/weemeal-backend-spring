package de.weemeal.backend.domain.service

import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.port.inbound.ForHandlingRecipePort
import de.weemeal.backend.domain.port.outbound.ForDeletingRecipe
import de.weemeal.backend.domain.port.outbound.ForLoadingRecipe
import de.weemeal.backend.domain.port.outbound.ForSavingRecipe
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RecipeService(
    private val forDeletingRecipe: ForDeletingRecipe,
    private val forLoadingRecipe: ForLoadingRecipe,
    private val forSavingRecipe: ForSavingRecipe,
) : ForHandlingRecipePort {

    override fun saveRecipe(recipe: Recipe): Recipe {
        return forSavingRecipe.save(recipe)
    }

    override fun getRecipe(recipeId: UUID): Recipe? {
        return forLoadingRecipe.findRecipe(recipeId)
    }

    override fun getAllRecipes(): List<Recipe>? {
        return forLoadingRecipe.findAllRecipes()
    }

    override fun deleteRecipe(recipeId: UUID): Boolean {
        forDeletingRecipe.deleteRecipe(recipeId)
        return forLoadingRecipe.findRecipe(recipeId) == null
    }
}