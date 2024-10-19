package de.weemeal.backend.domain.service

import de.weemeal.backend.domain.model.Recipe
import de.weemeal.backend.domain.model.ingredient.Ingredient
import de.weemeal.backend.domain.port.inbound.ForGettingIngredientList
import org.springframework.stereotype.Service

@Service
class IngredientService : ForGettingIngredientList {

    override fun filterIngredientsFromRecipe(recipe: Recipe): List<Ingredient> {
        return recipe.ingredientListContent
            .filterIsInstance<Ingredient>()
    }
}