package de.weemeal.backend.adapter.out.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "recipe")
data class RecipeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var recipeId: UUID?,
    var name: String? = null,
    var recipeYield: Int? = null,

    @Column(columnDefinition = "TEXT")
    var recipeInstructions: String? = null,

    @OneToMany(mappedBy = "recipe")
    var ingredients: List<IngredientEntity>? = null,
)