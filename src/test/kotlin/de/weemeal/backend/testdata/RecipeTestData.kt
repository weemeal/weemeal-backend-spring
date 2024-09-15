package de.weemeal.backend.testdata

import de.weemeal.backend.domain.model.Ingredient
import de.weemeal.backend.testdata.builder.RecipeTestDataBuilder
import java.util.UUID

class RecipeTestData {
    fun fullyBuild(amountOfIngredients: Int = 0): RecipeTestDataBuilder {
        return RecipeTestDataBuilder()
            .recipeId(UUID.randomUUID())
            .name("Pizza")
            .recipeYield(3)
            .recipeInstructions("Irgendein Text")
            .ingredients(generateIngredients(amountOfIngredients))
    }


    private fun generateIngredients(amountOfIngredients: Int = 0): List<Ingredient> {
        val ingredients = mutableListOf<Ingredient>()
        for (i in 0..amountOfIngredients) {
            ingredients.add(IngredientTestData().fullyBuild().build())
        }
        return ingredients
    }
}