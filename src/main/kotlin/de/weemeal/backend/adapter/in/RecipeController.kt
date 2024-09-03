package de.weemeal.backend.adapter.`in`

import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.port.`in`.RecipePort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@CrossOrigin(
    origins = [
        "http://localhost:3000/",
        "http://localhost:3003/",
        "http://192.168.178.36:4026/",
        "https://weemeal.darthkali.duckdns.org/"
    ]
)

@RestController
@RequestMapping("/api/recipes/")
class RecipeController(private val recipePort: RecipePort) {

    @PostMapping("")
    fun createRecipe(@RequestBody recipe: Recipe): ResponseEntity<Recipe> {
        val createdRecipe = recipePort.saveRecipe(recipe)
        return ResponseEntity(createdRecipe, HttpStatus.CREATED)
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

    @PutMapping("")
    fun updateRecipe(@RequestBody recipe: Recipe): ResponseEntity<Recipe> {
        return if (recipe.recipeId == null) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        } else {
            ResponseEntity(recipePort.saveRecipe(recipe), HttpStatus.OK)
        }
    }

    @DeleteMapping("{id}")
    fun deleteRecipe(
        @PathVariable("id") recipeId: String,
    ): ResponseEntity<Boolean> {
        return if (recipePort.deleteRecipe(recipeId = UUID.fromString(recipeId))) {
            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    // Neuer Endpunkt: Gibt ein statisches Rezept zurück
    @GetMapping("/static")
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

    // Neuer Endpunkt: Speichert ein Rezept mit statischen Werten und einem Namen aus der Pfadvariable
    @PostMapping("/create/{name}")
    fun createRecipeWithName(@PathVariable("name") name: String): ResponseEntity<Recipe> {
        val newRecipe = Recipe(
            recipeId = UUID.randomUUID(),
            name = name,
            recipeYield = 2,  // Statischer Wert
            recipeInstructions = "Kochen für 15 Minuten.",
            ingredients = listOf("Zutat A", "Zutat B")
        )
        val savedRecipe = recipePort.saveRecipe(newRecipe)
        return ResponseEntity(savedRecipe, HttpStatus.CREATED)
    }
}
