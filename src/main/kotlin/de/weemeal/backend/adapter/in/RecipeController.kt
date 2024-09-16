package de.weemeal.backend.adapter.`in`

import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.port.`in`.RecipePort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/recipes/")
class RecipeController(private val recipePort: RecipePort) {

    @PostMapping("")
    fun saveRecipe(@RequestBody recipe: Recipe): ResponseEntity<Recipe> {
        val savedRecipe = recipePort.saveRecipe(recipe)
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
        val recipe = recipePort.getRecipe(recipeId = UUID.fromString(recipeId))
        return ResponseEntity.ok(recipe)
    }

    @GetMapping
    fun getAllRecipes(): ResponseEntity<List<Recipe>> {
        return ResponseEntity.status(HttpStatus.OK).body(recipePort.getAllRecipes())
    }

    @DeleteMapping("{id}")
    fun deleteRecipe(
        @PathVariable("id") recipeId: String,
    ): ResponseEntity<Boolean> {
        return if (recipePort.deleteRecipe(UUID.fromString(recipeId))) {
            ResponseEntity(true, HttpStatus.OK)
        } else {
            ResponseEntity(false, HttpStatus.NOT_FOUND)
        }
    }
}
