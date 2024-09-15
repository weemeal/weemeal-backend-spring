package de.weemeal.backend.domain.service

import de.weemeal.backend.domain.model.Ingredient
import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.port.out.RecipeRepositoryPort
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.UUID

class RecipeServiceTest {
    private lateinit var recipeRepositoryPort: RecipeRepositoryPort
    private lateinit var recipeService: RecipeService


    @BeforeEach
    fun setup() {
        recipeRepositoryPort = mock()
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

        val tomaten =
            Ingredient(ingredientId = UUID.randomUUID(), ingredientName = "Tomaten", unit = "g", amount = "200")
        val cheese = Ingredient(ingredientId = UUID.randomUUID(), ingredientName = "Cheese", unit = "ml", amount = "5")
        val basil = Ingredient(ingredientId = UUID.randomUUID(), ingredientName = "Basil", unit = "stk", amount = "3")
        val brot = Ingredient(ingredientId = UUID.randomUUID(), ingredientName = "Brot", unit = "stk", amount = "5")

        val existingRecipe = Recipe(
            recipeId = recipeId,
            name = "Pizza",
            recipeYield = 5,
            recipeInstructions = "instruction",
            ingredients = listOf(tomaten, cheese)
        )

        val updatedRecipe = Recipe(
            recipeId = recipeId,
            name = "Pizza Neu",
            recipeYield = 4,
            recipeInstructions = "instruction auch neu",
            ingredients = listOf(tomaten, basil, brot)
        )

        whenever(recipeRepositoryPort.findRecipe(recipeId)).thenReturn(existingRecipe)
        whenever(recipeRepositoryPort.save(any())).thenAnswer { it.arguments[0] }

        val result = recipeService.updateRecipe(updatedRecipe)

        assertEquals(updatedRecipe.ingredients.size, result.ingredients.size)
        verify(recipeRepositoryPort, times(1)).removeIngredient(eq(cheese))
        verify(recipeRepositoryPort, times(1)).save(eq(updatedRecipe))
    }

    @Test
    fun `should throw exception when recipe not found`() {
        whenever(recipeRepositoryPort.findRecipe(any())).thenReturn(null)

        val exception = assertThrows<IllegalArgumentException> {
            recipeService.updateRecipe(Recipe())
        }
        assertEquals("Rezept nicht gefunden", exception.message)
    }
}