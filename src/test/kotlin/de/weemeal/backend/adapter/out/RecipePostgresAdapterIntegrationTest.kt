import de.weemeal.backend.WeemealBackendSpringApplication
import de.weemeal.backend.adapter.out.RecipePostgresAdapter
import de.weemeal.backend.domain.model.Ingredient
import de.weemeal.backend.domain.model.Recipe
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@SpringBootTest(classes = [WeemealBackendSpringApplication::class])
@ActiveProfiles("test")
@Transactional
class RecipePostgresAdapterIntegrationTest {

    @Autowired
    lateinit var recipePostgresAdapter: RecipePostgresAdapter

    @Test
    fun `should save and load recipe from database`() {
        // Arrange
        val ingredient1 = Ingredient(UUID.randomUUID(), "Tomato", "g", "2")
        val ingredient2 = Ingredient(UUID.randomUUID(), "Salt", "ml", "1")
        val ingredients = listOf(ingredient1, ingredient2)

        val recipe = Recipe(UUID.randomUUID(), "Test Recipe", 4, "Instructions",ingredients)

        // Act
        val savedRecipe = recipePostgresAdapter.save(recipe)
        val loadedRecipe = savedRecipe.recipeId?.let { recipePostgresAdapter.findRecipe(it) }

        // Assert
        assertNotNull(loadedRecipe)
        assertEquals("Test Recipe", loadedRecipe?.name)

        // Prüfe die Zutaten
        val loadedIngredients = loadedRecipe?.ingredients ?: emptyList()
        assertEquals("Tomato", loadedIngredients[0].ingredientName)
        assertEquals("Salt", loadedIngredients[1].ingredientName)
    }
}
