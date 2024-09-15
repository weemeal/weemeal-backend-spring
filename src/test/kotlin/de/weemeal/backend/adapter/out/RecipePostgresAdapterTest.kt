import de.weemeal.backend.adapter.mapper.RecipeMapper.toEntity
import de.weemeal.backend.adapter.out.RecipePostgresAdapter
import de.weemeal.backend.adapter.out.persistence.RecipeRepository
import de.weemeal.backend.adapter.out.persistence.entity.IngredientRepository
import de.weemeal.backend.adapter.out.persistence.entity.RecipeEntity
import de.weemeal.backend.domain.model.Ingredient
import de.weemeal.backend.domain.model.Recipe
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import java.util.Optional
import java.util.UUID

class RecipePostgresAdapterTest {

    private lateinit var recipeRepository: RecipeRepository
    private lateinit var ingredientRepository: IngredientRepository
    private lateinit var recipePostgresAdapter: RecipePostgresAdapter

    @BeforeEach
    fun setUp() {
        recipeRepository = mock(RecipeRepository::class.java)
        ingredientRepository = mock(IngredientRepository::class.java)
        recipePostgresAdapter = RecipePostgresAdapter(recipeRepository, ingredientRepository)
    }

    @Test
    fun `should save a recipe`() {
        val recipe = Recipe(UUID.randomUUID(), "Test Recipe", 4, "Instructions", emptyList())
        val recipeEntity = recipe.toEntity()

        `when`(recipeRepository.save(recipeEntity)).thenReturn(recipeEntity)

        val savedRecipe = recipePostgresAdapter.save(recipe)

        assertEquals(recipe, savedRecipe)
        verify(recipeRepository, times(1)).save(recipeEntity)
    }

    @Test
    fun `should save a recipe with ingredients`() {
        val ingredient1 = Ingredient(UUID.randomUUID(), "Flour", "2", "cups")
        val ingredient2 = Ingredient(UUID.randomUUID(), "Sugar", "1", "cup")
        val ingredients = listOf(ingredient1, ingredient2)

        val recipe = Recipe(UUID.randomUUID(), "Test Recipe", 4, "Instructions", ingredients)
        val recipeEntity = recipe.toEntity()

        `when`(recipeRepository.save(recipeEntity)).thenReturn(recipeEntity)

        val savedRecipe = recipePostgresAdapter.save(recipe)

        assertEquals(recipe.recipeId, savedRecipe.recipeId)
        assertEquals(recipe.name, savedRecipe.name)
        assertEquals(recipe.recipeYield, savedRecipe.recipeYield)
        assertEquals(recipe.recipeInstructions, savedRecipe.recipeInstructions)
        assertEquals(recipe.ingredients.size, savedRecipe.ingredients.size)

        savedRecipe.ingredients.let {
            assertEquals("Flour", it[0].ingredientName)
            assertEquals("2", it[0].unit)
            assertEquals("cups", it[0].amount)
            assertEquals("Sugar", it[1].ingredientName)
            assertEquals("1", it[1].unit)
            assertEquals("cup", it[1].amount)
        }

        verify(recipeRepository, times(1)).save(recipeEntity)
    }

    @Test
    fun `should find a recipe by id`() {
        val recipeId = UUID.randomUUID()
        val recipeEntity = RecipeEntity(recipeId, "Test Recipe", 4, "Instructions", emptyList())
        `when`(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipeEntity))

        val recipe = recipePostgresAdapter.findRecipe(recipeId)

        assertNotNull(recipe)
        assertEquals(recipeId, recipe?.recipeId)
        verify(recipeRepository, times(1)).findById(recipeId)
    }

    @Test
    fun `should delete a recipe by id`() {
        val recipeId = UUID.randomUUID()

        recipePostgresAdapter.deleteRecipe(recipeId)

        verify(recipeRepository, times(1)).deleteById(recipeId)
    }


}
