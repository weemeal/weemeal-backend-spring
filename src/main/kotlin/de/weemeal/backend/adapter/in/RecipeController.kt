package de.weemeal.backend.adapter.`in`

import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.ports.`in`.RecipePort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/recipes/")
class RecipeController(private val recipePort: RecipePort) {

    @GetMapping
    fun getAllRecipes(): ResponseEntity<List<Recipe>> {
        return ResponseEntity.status(HttpStatus.OK).body(recipePort.getAllRecipes())
    }

    @GetMapping("2")
    fun getAllRecipess(): ResponseEntity<List<Recipe>> {
        return ResponseEntity.status(HttpStatus.OK).body(
            listOf(
                Recipe(recipeId = UUID.randomUUID()),
                Recipe(recipeId = UUID.randomUUID()),
                Recipe(recipeId = UUID.randomUUID()),
            )
        )
    }

    @GetMapping("/test")
    fun getAllRecipes2(): String {
        return "Hallo"
    }

    @PostMapping("")
    fun createRecipe(@RequestBody recipe: Recipe): ResponseEntity<Recipe> {
        val createdRecipe = recipePort.createRecipe(recipe)
        return ResponseEntity(createdRecipe, HttpStatus.CREATED)
    }


}