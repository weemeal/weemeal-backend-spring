package de.weemeal.backend.testdata

import de.weemeal.backend.domain.model.RecipeId
import de.weemeal.backend.domain.model.ingredient.Ingredient
import de.weemeal.backend.testdata.builder.RecipeTestDataBuilder

class RecipeTestData {
    fun fullyBuild(amountOfIngredients: Int = 0): RecipeTestDataBuilder {
        return RecipeTestDataBuilder()
            .recipeId(RecipeId())
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