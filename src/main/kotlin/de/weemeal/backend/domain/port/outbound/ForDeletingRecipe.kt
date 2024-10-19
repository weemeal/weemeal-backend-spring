package de.weemeal.backend.domain.port.outbound

import java.util.UUID

interface ForDeletingRecipe {
    fun deleteRecipe(recipeId: UUID)
}