package de.weemeal.backend.adapter.`in`

import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.port.`in`.RecipePort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/test/")
class TestController(private val recipePort: RecipePort) {

    @GetMapping()
    fun getStaticRecipe(): ResponseEntity<Recipe> {
        val staticRecipe = Recipe(
            recipeId = UUID.randomUUID(),
            name = "Statisches Rezept",
            recipeYield = 4,
            recipeInstructions = "Mische alle Zutaten zusammen und koche für 20 Minuten.",
            ingredients = listOf("Zutat 1", "Zutat 2", "Zutat 3")
        )
        return ResponseEntity.ok(staticRecipe)
    }

    @PostMapping()
    fun createRecipeWithName(): ResponseEntity<Recipe> {
        val newRecipe = Recipe(
            recipeId = UUID.randomUUID(),
            name = "TestRezept",
            recipeYield = 2,  // Statischer Wert
            recipeInstructions = "Kochen für 15 Minuten.",
            ingredients = listOf("Zutat A", "Zutat B")
        )
        val savedRecipe = recipePort.saveRecipe(newRecipe)
        return ResponseEntity(savedRecipe, HttpStatus.CREATED)
    }
}
