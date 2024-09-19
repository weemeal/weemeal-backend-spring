package de.weemeal.backend.domain.service

import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.port.`in`.RecipePort
import de.weemeal.backend.domain.port.out.RecipeImageGeneratorPort
import de.weemeal.backend.domain.port.out.RecipeRepositoryPort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RecipeService(
    private val recipeRepositoryPort: RecipeRepositoryPort,
    private val recipeImageGeneratorPort: RecipeImageGeneratorPort,
) : RecipePort {

    override fun saveRecipe(recipe: Recipe): Recipe {

//        if (recipe.imageUrl == null){
//            recipe.imageUrl = recipeImageGeneratorPort.generateRecipeImage(recipe)
//        }
        println("Hier wird versucht ein Bild zu finden")
        println(recipe.name)
        println("ImageUrl")
        println(recipeImageGeneratorPort.generateRecipeImage(recipe))
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
}