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

@RestController
@RequestMapping("/api/recipes")
class RecipeController(private val recipePort: RecipePort) {

    @GetMapping("")
    fun getAllRecipes(): List<Recipe> {
        return recipePort.getAllRecipes()
    }

    @PostMapping("")
    fun createRecipe(@RequestBody recipe: Recipe): ResponseEntity<Recipe> {
        val createdRecipe = recipePort.createRecipe(recipe)
        return ResponseEntity(createdRecipe, HttpStatus.CREATED)
    }


}