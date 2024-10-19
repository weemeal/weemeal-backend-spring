package de.weemeal.backend.domain.model

import de.weemeal.backend.domain.model.ingredient.IngredientListContent

data class Recipe(
    var recipeId: RecipeId,
    var name: String? = null,
    var recipeYield: Int? = null,
    var recipeInstructions: String? = null,
    var ingredientListContent: List<IngredientListContent> = emptyList(),
)