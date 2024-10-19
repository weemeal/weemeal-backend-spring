package de.weemeal.backend.domain.service

import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.port.outbound.ForDeletingRecipe
import de.weemeal.backend.domain.port.outbound.ForLoadingRecipe
import de.weemeal.backend.domain.port.outbound.ForSavingRecipe
import de.weemeal.backend.testdata.IngredientTestData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.UUID

class RecipeServiceTest {

    private var forDeletingRecipe: ForDeletingRecipe = mockk()
    private var forLoadingRecipe: ForLoadingRecipe = mockk()
    private var forSavingRecipe: ForSavingRecipe = mockk()
    private lateinit var recipeService: RecipeService


    @BeforeEach
    fun setup() {
        recipeService = RecipeService(forDeletingRecipe, forLoadingRecipe, forSavingRecipe)
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
            ingredientListContent = listOf(tomatoes, cheese)
        )

        val updatedRecipe = Recipe(
            recipeId = recipeId,
            name = "Pizza Neu",
            recipeYield = 4,
            recipeInstructions = "instruction auch neu",
            ingredientListContent = listOf(tomatoes, basil, bread)
        )

        every { forLoadingRecipe.findRecipe(recipeId) } returns existingRecipe
        every { forSavingRecipe.save(any<Recipe>()) } returns updatedRecipe

        val result = recipeService.saveRecipe(updatedRecipe)

        assertEquals(updatedRecipe.ingredientListContent.size, result.ingredientListContent.size)
        verify(exactly = 1) { forSavingRecipe.save(updatedRecipe) }
    }
}

