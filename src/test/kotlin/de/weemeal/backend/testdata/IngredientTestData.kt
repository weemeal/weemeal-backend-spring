package de.weemeal.backend.testdata

import de.weemeal.backend.testdata.builder.IngredientTestDataBuilder
import java.util.UUID

class IngredientTestData {
    fun fullyBuild(): IngredientTestDataBuilder {
        return IngredientTestDataBuilder()
            .ingredientId(UUID.randomUUID())
            .ingredientName("Tomaten")
            .amount("5")
            .unit("Stk")
    }
}
