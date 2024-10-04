package de.weemeal.backend.adapter.out

import de.weemeal.backend.testdata.IngredientTestData
import de.weemeal.backend.testdata.RecipeTestData
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class RecipePostgresAdapterIntegrationTest {

    @Autowired
    lateinit var recipePostgresAdapter: RecipePostgresAdapter

    @Test
    fun `should save and load recipe from database`() {

        val tomato = IngredientTestData().fullyBuild().ingredientName("Tomaten").unit("g").amount(2F).build()
        val salt = IngredientTestData().fullyBuild().ingredientName("Salz").unit("ml").amount(1F).build()

        val recipe = RecipeTestData().fullyBuild()
            .name("Test Recipe")
            .recipeYield(4)
            .recipeInstructions("Instructions")
            .ingredients(listOf(tomato, salt))
            .build()

        val savedRecipe = recipePostgresAdapter.save(recipe)
        val loadedRecipe = savedRecipe.recipeId?.let { recipePostgresAdapter.findRecipe(it) }

        assertNotNull(loadedRecipe)
        assertEquals("Test Recipe", loadedRecipe?.name)

        val loadedIngredients = loadedRecipe?.ingredients ?: emptyList()
        assertEquals(tomato.ingredientName, loadedIngredients[0].ingredientName)
        assertEquals(salt.ingredientName, loadedIngredients[1].ingredientName)
    }
}
