package de.weemeal.backend.domain.port.outbound

import de.weemeal.backend.domain.model.Recipe

interface ForSavingRecipe {
    fun save(recipe: Recipe): Recipe
}