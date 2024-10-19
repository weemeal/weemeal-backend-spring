package de.weemeal.backend.testdata.builder

import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.model.RecipeId
import de.weemeal.backend.domain.model.ingredient.Ingredient

class RecipeTestDataBuilder {
    private var recipeId: RecipeId? = null
    private var name: String? = null
    private var recipeYield: Int? = null
    private var recipeInstructions: String? = null
    private var ingredients: List<Ingredient>? = null

    fun recipeId(recipeId: RecipeId) = apply { this.recipeId = recipeId }
    fun name(name: String) = apply { this.name = name }
    fun recipeYield(recipeYield: Int) = apply { this.recipeYield = recipeYield }
    fun recipeInstructions(recipeInstructions: String) = apply { this.recipeInstructions = recipeInstructions }
    fun ingredients(ingredients: List<Ingredient>) = apply { this.ingredients = ingredients }


    fun build(): Recipe = Recipe(
        recipeId = recipeId!!,
        name = name,
        recipeYield = recipeYield,
        recipeInstructions = recipeInstructions,
        ingredientListContent = ingredients!!
    )
}