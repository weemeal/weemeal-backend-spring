package de.weemeal.backend.domain.model

import java.util.UUID


@JvmInline
value class RecipeId(val value: UUID = UUID.randomUUID()) {
    companion object {
        fun UUID.toRecipeId(): RecipeId = RecipeId(this)
        fun String.toRecipeId(): RecipeId = RecipeId(UUID.fromString(this))
    }
}