package de.weemeal.backend.adapter.`in`

import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.port.inbound.ForGettingIngredientList
import de.weemeal.backend.domain.port.inbound.ForHandlingRecipePort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@RestController
@RequestMapping("/api/recipes/")
class RecipeController(
    private val forHandlingRecipePort: ForHandlingRecipePort,
    private val forGettingIngredientList: ForGettingIngredientList,
) {

    @PostMapping("")
    fun saveRecipe(@RequestBody recipe: Recipe): ResponseEntity<Recipe> {
        val savedRecipe = forHandlingRecipePort.saveRecipe(recipe)
        return if (recipe.recipeId == null) {
            ResponseEntity(savedRecipe, HttpStatus.CREATED)
        } else {
            ResponseEntity(savedRecipe, HttpStatus.OK)
        }
    }

    @GetMapping("{id}")
    fun getRecipe(
        @PathVariable("id") recipeId: String,
    ): ResponseEntity<Recipe> {
        val recipe = forHandlingRecipePort.getRecipe(recipeId = UUID.fromString(recipeId))
        return ResponseEntity.ok(recipe)
    }

    @GetMapping
    fun getAllRecipes(): ResponseEntity<List<Recipe>> {
        return ResponseEntity.status(HttpStatus.OK).body(forHandlingRecipePort.getAllRecipes())
    }

    @DeleteMapping("{id}")
    fun deleteRecipe(
        @PathVariable("id") recipeId: String,
    ): ResponseEntity<Boolean> {
        return if (forHandlingRecipePort.deleteRecipe(UUID.fromString(recipeId))) {
            ResponseEntity(true, HttpStatus.OK)
        } else {
            ResponseEntity(false, HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("bring/{id}")
    fun getBringRecipe(
        @PathVariable("id") recipeId: UUID,
    ): ResponseEntity<String> {
        val recipe = forHandlingRecipePort.getRecipe(recipeId = recipeId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

        val ingredientList = forGettingIngredientList.filterIngredientsFromRecipe(recipe)

        val ingredientsHtml = ingredientList.joinToString(separator = "\n") {
            "<div itemprop=\"ingredients\">${it.amount ?: ""} ${it.unit} ${it.ingredientName}</div>"
        }

        val recipeHtml = """
            <div itemtype="http://schema.org/Recipe">
            <div itemprop="name">${recipe.name}</div>
            <div itemprop="yield">${recipe.recipeYield}</div>
            <div itemprop="recipeInstructions">${recipe.recipeInstructions}</div>
            $ingredientsHtml
            </div>
            """
        return ResponseEntity.ok(recipeHtml)
    }
}
