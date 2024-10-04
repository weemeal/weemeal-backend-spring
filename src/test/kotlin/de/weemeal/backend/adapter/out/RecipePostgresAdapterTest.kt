package de.weemeal.backend.adapter.out

import de.weemeal.backend.adapter.mapper.RecipeMapper.toEntity
import de.weemeal.backend.adapter.out.persistence.RecipeRepository
import de.weemeal.backend.adapter.out.persistence.entity.RecipeEntity
import de.weemeal.backend.testdata.IngredientTestData
import de.weemeal.backend.testdata.RecipeTestData
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Optional
import java.util.UUID

class RecipePostgresAdapterTest {

    private lateinit var recipeRepository: RecipeRepository
    private lateinit var recipePostgresAdapter: RecipePostgresAdapter

    @BeforeEach
    fun setUp() {
        recipeRepository = mockk<RecipeRepository>()
        recipePostgresAdapter = RecipePostgresAdapter(recipeRepository)
    }

    @Test
    fun `should save a recipe`() {
        val recipe = RecipeTestData().fullyBuild().build()

        val recipeEntity = recipe.toEntity()

        every { recipeRepository.save(recipeEntity) } returns recipeEntity

        val savedRecipe = recipePostgresAdapter.save(recipe)

        assertEquals(recipe, savedRecipe)
        verify(exactly = 1) { recipeRepository.save(recipeEntity) }
    }

    @Test
    fun `should save a recipe with ingredients`() {

        val ingredients = listOf(
            IngredientTestData().fullyBuild().ingredientName("Tomaten").unit("g").amount(2F).build(),
            IngredientTestData().fullyBuild().ingredientName("Salz").unit("ml").amount(1F).build(),
            IngredientTestData().fullyBuild().ingredientName("Brot").unit("kg").amount(100F).build(),
        )

        val recipe = RecipeTestData().fullyBuild()
            .name("Test Recipe")
            .recipeYield(4)
            .recipeInstructions("Instructions")
            .ingredients(ingredients)
            .build()

        val recipeEntity = recipe.toEntity()

        every { recipeRepository.save(recipeEntity) } returns recipeEntity

        val savedRecipe = recipePostgresAdapter.save(recipe)

        assertEquals(recipe.recipeId, savedRecipe.recipeId)
        assertEquals(recipe.name, savedRecipe.name)
        assertEquals(recipe.recipeYield, savedRecipe.recipeYield)
        assertEquals(recipe.recipeInstructions, savedRecipe.recipeInstructions)
        assertEquals(recipe.ingredients.size, savedRecipe.ingredients.size)

        savedRecipe.ingredients.let { savedIngredients ->
            savedIngredients.forEachIndexed { index, savedIngredient ->
                assertEquals(savedIngredient.ingredientName, ingredients[index].ingredientName)
                assertEquals(savedIngredient.amount, ingredients[index].amount)
                assertEquals(savedIngredient.unit, ingredients[index].unit)
            }
        }

        verify(exactly = 1) { recipeRepository.save(recipeEntity) }
    }

    @Test
    fun `should find a recipe by id`() {
        val recipeId = UUID.randomUUID()
        val recipeEntity = RecipeEntity(recipeId, "Test Recipe", 4, "Instructions", emptyList())

        every { recipeRepository.findById(recipeId) } returns Optional.of(recipeEntity)

        val recipe = recipePostgresAdapter.findRecipe(recipeId)

        assertNotNull(recipe)
        assertEquals(recipeId, recipe?.recipeId)
        verify(exactly = 1) { recipeRepository.findById(recipeId) }
    }

    @Test
    fun `should delete a recipe by id`() {
        val recipeId = UUID.randomUUID()

        every { recipeRepository.deleteById(recipeId) } just Runs
        recipePostgresAdapter.deleteRecipe(recipeId)

        verify(exactly = 1) { recipeRepository.deleteById(recipeId) }
    }
}