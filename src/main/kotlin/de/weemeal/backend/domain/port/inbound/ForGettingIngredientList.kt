package de.weemeal.backend.domain.port.inbound

import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.model.ingredient.Ingredient

interface ForGettingIngredientList {
    fun filterIngredientsFromRecipe(recipe: Recipe): List<Ingredient>
}