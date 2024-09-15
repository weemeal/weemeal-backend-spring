package de.weemeal.backend.domain.service

import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.port.out.RecipeRepositoryPort
import de.weemeal.backend.testdata.IngredientTestData
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class RecipeServiceTest {
    private lateinit var recipeRepositoryPort: RecipeRepositoryPort
    private lateinit var recipeService: RecipeService


    @BeforeEach
    fun setup() {
        recipeRepositoryPort = mockk()
        recipeService = RecipeService(recipeRepositoryPort)
    }

    @Test
    fun saveRecipe() {
    }

    @Test
    fun getRecipe() {
    }

    @Test
    fun getAllRecipes() {
    }

    @Test
    fun deleteRecipe() {
    }

    @Test
    fun updateRecipe() {
    }

    @Test
    fun `should update recipe and remove ingredients`() {
        val recipeId = UUID.randomUUID()

        val tomatoes = IngredientTestData().fullyBuild().ingredientName("Tomaten").build()
        val cheese = IngredientTestData().fullyBuild().ingredientName("Käse").build()
        val basil = IngredientTestData().fullyBuild().ingredientName("Basilikum").build()
        val bread = IngredientTestData().fullyBuild().ingredientName("Brot").build()

        val existingRecipe = Recipe(
            recipeId = recipeId,
            name = "Pizza",
            recipeYield = 5,
            recipeInstructions = "instruction",
            ingredients = listOf(tomatoes, cheese)
        )

        val updatedRecipe = Recipe(
            recipeId = recipeId,
            name = "Pizza Neu",
            recipeYield = 4,
            recipeInstructions = "instruction auch neu",
            ingredients = listOf(tomatoes, basil, bread)
        )

        every { recipeRepositoryPort.findRecipe(recipeId) } returns existingRecipe
        every { recipeRepositoryPort.save(any<Recipe>()) } returns updatedRecipe
        every { recipeRepositoryPort.removeIngredient(any()) } just Runs

        val result = recipeService.updateRecipe(updatedRecipe)

        assertEquals(updatedRecipe.ingredients.size, result.ingredients.size)
        verify(exactly = 1) { recipeRepositoryPort.removeIngredient(cheese) }
        verify(exactly = 1) { recipeRepositoryPort.save(updatedRecipe) }
    }

    @Test
    fun `should throw exception when recipe not found`() {
        every { recipeRepositoryPort.findRecipe(any()) } returns null

        val exception = assertThrows<IllegalArgumentException> {
            recipeService.updateRecipe(Recipe())
        }
        assertEquals("Rezept nicht gefunden", exception.message)
    }
}

