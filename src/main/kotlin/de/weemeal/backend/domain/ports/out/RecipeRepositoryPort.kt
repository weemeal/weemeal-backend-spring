package de.weemeal.backend.domain.ports.out

import de.weemeal.backend.domain.model.Recipe

interface RecipeRepositoryPort {
    fun findAllRecipes(): List<Recipe>
    fun save(recipe: Recipe): Recipe
}