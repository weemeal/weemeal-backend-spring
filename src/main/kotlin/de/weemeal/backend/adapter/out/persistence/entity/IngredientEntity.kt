package de.weemeal.backend.adapter.out.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "ingredient")
data class IngredientEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var ingredientId: UUID?,
    var ingredientName: String? = null,
    var unit: String? = null,
    var amount: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    var recipe: RecipeEntity
)
